package Control;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import Boundary.MainPage;
import Boundary.Base64EncodeConversion;


public class UserAuthenticationControl {
    private String userId;
    private String password;
    private String fname;
    private String lname;
    private String ppsn;
    private String address;
    private String eircode;
    private String email;
    public String baseURL = "https://falconer2-71714182580c.herokuapp.com/";

    public UserAuthenticationControl(String userId, String password){
        this.userId = userId;
        this.password = Base64EncodeConversion.encodeString(password);
    }

    public UserAuthenticationControl(String fname, String lname, String username, String password, String ppsn, String address, String eircode, String email){
        this.userId = username;
        this.password = Base64EncodeConversion.encodeString(password);
        this.fname = fname;
        this.lname = lname;
        this.ppsn = ppsn;
        this.address = address;
        this.eircode = eircode;
        this.email = email;
    }

    public boolean authenticate() {
        try {
            var uri = URI.create(baseURL+"login");
            String jsonData = "{\"username\":\""+userId+"\",\"password\":\""+password+"\"}";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            String jsonString = String.valueOf(response.body());
            //JSONObject jsonObject = new JSONObject(response.body());
            if (response.statusCode() == 200 && !jsonString.contains("error"))
            return true;
            else
            return false;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register() {
        try {
            var uri = URI.create(baseURL+"register");
            String jsonData = "{\"first_name\":\""+fname+"\",\"last_name\":\""+lname+"\",\"username\":\""+userId+"\",\"password\":\""+password+"\",\"PPSN\":\""+ppsn+"\",\"address\":\""+address+"\",\"eir_code\":\""+eircode+"\",\"email\":\""+email+"\"}";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            if (response.statusCode() == 200)
            return true;
            else
            return false;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
