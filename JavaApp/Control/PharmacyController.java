package Control;

import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;

public class PharmacyController implements IObserver {

    public void update(String orderId) {
        manageInventory(orderId);
    }

    public void manageInventory(String orderId) {
        try {
            var uri = URI.create("https://falconer2-71714182580c.herokuapp.com/updateInventory");
            String jsonData = "{\"order_number\":\""+orderId+"\"}";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Inventory updated after cancellation.");
            } else {
                System.out.println("Error occurred during cancellation. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}