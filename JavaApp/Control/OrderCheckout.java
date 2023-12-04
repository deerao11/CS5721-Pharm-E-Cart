package Control;

import java.util.*;
import Entity.CartWrapper;
import Entity.CustomerDetail;
import Entity.OrderWrapper;
import Entity.UpdateWrapper;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import Entity.CartWrapper;

public class OrderCheckout {
   
    public String baseURL = "https://falconer2-71714182580c.herokuapp.com/";
    String jsonData="[";
    int customerID ;

    public List<OrderWrapper> checkout(List<CartWrapper> cw, CustomerDetail cd, String deliveryOptions) {
         List<UpdateWrapper> updatewrap= new ArrayList<>();
         Scanner input = new Scanner(System.in);
    	List<OrderWrapper> orderwrapper = new ArrayList<>();
        for(int i=0; i < cw.size(); i++) {
            customerID=cw.get(i).custId;
            jsonData += "{\"category_id\":\""+cw.get(i).categoryId+"\",\"product_id\":\""+cw.get(i).prodId+"\",\"customer_id\":\""+cw.get(i).custId+"\",\"quantity\":\""+cw.get(i).quantity+"\",\"price\":\""+cw.get(i).price+"\"}";
            if (i < cw.size() - 1) {
                jsonData +=  ",";
            } else {
                jsonData +=  "]";
            }
        }
        try {
            System.out.println(jsonData);
                var uri = URI.create(baseURL+"addtocart");
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
                    String cartNum = jsonObject.getString("OrderNo");
                    System.out.println("Items have been checked out to the cart. Your order is eligible for "+deliveryOptions);
                    OrderWrapper ow  = new OrderWrapper(customerID, cartNum, deliveryOptions);
                 // UpdateWrapper uw=new UpdateWrapper(customerID,);
                    orderwrapper.add(ow);
                    Order oo = new Order();
                    oo.OrderDetail(orderwrapper, cw);
                    // orderwrapper.add(uw);
                    //oo.updateState(;

                    
                }
                else
                System.out.println("Error moving things into cart");
            }catch (Exception e) {
                e.printStackTrace();
            }
        return orderwrapper;
    }
}
