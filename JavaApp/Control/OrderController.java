package Control;

import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import org.json.JSONObject;

public class OrderController implements IObservable {

    public ArrayList<IObserver> observerList = new ArrayList<IObserver>();
    public void addObs(IObserver obs) {
        observerList.add(obs);
    }

    public void removeObs(IObserver obs) {
        observerList.remove(obs);
    }

    public void notifyObservers(String orderId) {
        observerList.forEach(observer -> observer.update(orderId));
    }

    public String getOrderStatus(String orderId) {
        try {
            var uri = URI.create("https://falconer2-71714182580c.herokuapp.com/getOrderStatus");
            String jsonData = "{\"orderId\":\""+orderId+"\"}";
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
            if (response.statusCode() == 200) {
                JSONObject jsonObject = new JSONObject(response.body());
                String status = jsonObject.getString("status");
                if (status == "Cancelled")
                notifyObservers(orderId);
            }
            else
            return "Error Occured";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Please try again";
    }

}
