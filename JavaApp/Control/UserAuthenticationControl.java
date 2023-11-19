package Control;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;



public class UserAuthenticationControl {
    private String userId;
    private String password;

    public UserAuthenticationControl(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

        
    public boolean authenticate() {
        try {
            var uri = URI.create("https://falconer2-71714182580c.herokuapp.com/login");
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

            if (response.statusCode() == 200)
            return true;
            else
            return false;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String fname, String lname, String username, String password, String ppsn, String address, String eircode, String email) {
        try {
            var uri = URI.create("https://falconer2-71714182580c.herokuapp.com/register");
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
