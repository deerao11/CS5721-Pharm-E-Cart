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
import Control.Order;

public class PlaceOrder {
	public String baseURL = "https://falconer2-71714182580c.herokuapp.com/";
    public String jsonData;

    public List<UpdateWrapper> placeOrder(List<OrderWrapper> ow) {
    	List<UpdateWrapper> cancelwrapper = new ArrayList<>();
       String Delivery_type= ow.get(0).delType;
    	jsonData = "{ \"order_number\" : \""+ow.get(0).orderId+"\",\"delivery_type\": \""+ow.get(0).delType+"\", \"customer_id\":\""+ow.get(0).custId+"\"}";
        
        try {
            System.out.println(jsonData);
                var uri = URI.create(baseURL+"placeOrder");
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
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    String orderId = jsonObject.getString("OrderNo");
                    String orderTime = jsonObject.getString("ordertime");
                    UpdateWrapper cw = new UpdateWrapper(orderId, orderTime,ow.get(0).custId);
                    cancelwrapper.add(cw);
                    // Order od = new Order();
                    // od.updateOrder(cancelwrapper);
                     OrderContext orderContext = new OrderContext();
                    orderContext.updateOrder(cancelwrapper, Delivery_type);
                }
                else
                    System.out.println("Error placing order.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
		return cancelwrapper;
    }
}