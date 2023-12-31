package Control;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Entity.UpdateWrapper;
import Entity.OrderWrapper;
import Control.OrderController;

public class UpdateOrder {
	public String baseURL = "https://falconer2-71714182580c.herokuapp.com/";
    public String jsonData;
    public String orderNo;

    /*
     * This method is called when the status of the order changes in any way. It calls the
     * database through the API to update the same.
     */
    public String updateOrder(List<UpdateWrapper> cw, String updateStatus) {
    	jsonData = "{\"customer_id\":\""+cw.get(0).custId+"\",\"order_number\":\""+cw.get(0).orderId+"\",\"order_status\":\""+updateStatus+"\"}";
        
        
        try {
            System.out.println(jsonData);
                var uri = URI.create(baseURL+"updateOderStatus");
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest
                        .newBuilder()
                        .uri(uri)
                        .header("accept", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                        .build();
                HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.statusCode());
                if (response.statusCode() == 200) {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    System.out.println(jsonObject.get("msg"));
                    /*
                     * When the order is cancelled we would like to invoke the Observable so that it
                     * can notify the observers of the change.
                     */
                    if (updateStatus.equals("Cancelled")) {
                        orderNo = jsonObject.getString("OrderNo");
                        OrderController oc = new OrderController();
                        IObserver observer1 = new PharmacyController();
                        oc.addObs(observer1);
                        oc.notifyObservers(orderNo);
                    }
                }
                else
                    System.out.println("Error updating status.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
		return orderNo;
    }
}