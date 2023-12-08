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
    DeliveryCostCalc deliveryCostCalc = new DeliveryCostCalc();
    public String baseURL = "https://falconer2-71714182580c.herokuapp.com/";
    String jsonData="[";
    int customerID ;

    //This method is called when the customer clicks on "Add to cart"
    public List<OrderWrapper> checkout(List<CartWrapper> cw, CustomerDetail cd, String deliveryOptions) {
        double totalItemCost = 0,deliveryCost=0;
        // this is for the redundant order qty prob.

        Map<String, Integer> existingProducts = new HashMap<>();
        Map<String, Double> productPrice = new HashMap<>();
        List<UpdateWrapper> updatewrap= new ArrayList<>();
        Scanner input = new Scanner(System.in);
    	List<OrderWrapper> orderwrapper = new ArrayList<>();
        //This loop checks if a product has already been selected and increments the quantity of product accordingly 
        for(int i=0; i < cw.size(); i++) {
            customerID=cw.get(i).custId;
            final String uniqId = cw.get(i).categoryId + "-" + cw.get(i).prodId+"-"+cw.get(i).getCustId();
            if (existingProducts.containsKey(uniqId)) {
                int newQty = existingProducts.get(uniqId) + cw.get(i).getQuantity();
                existingProducts.put(uniqId, newQty);
            } else {
                existingProducts.put(uniqId, cw.get(i).getQuantity());
            }
            productPrice.put(uniqId, cw.get(i).getPrice());
        }
        int count=0;
        // This for loop is to construct the jsonBody properly in case there are multiple products in an order
       for(Map.Entry<String, Integer> entry: existingProducts.entrySet()){
           String splitted[]= entry.getKey().split("-");
           double price = productPrice.get(entry.getKey())*entry.getValue();
           jsonData += "{\"category_id\":\"" + splitted[0] + "\",\"product_id\":\"" + splitted[1] + "\",\"customer_id\":\"" + splitted[2] + "\",\"quantity\":\"" + entry.getValue() + "\",\"price\":\"" + price + "\"}";
           if (count < existingProducts.size() - 1) {
               jsonData +=  ",";
           } else {
               jsonData +=  "]";
           }
           count++;
           totalItemCost +=price;
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
                    System.out.println("Items have been checked out to the cart. Your order is eligible for " + deliveryOptions);
                    // This is the delivery decorator. but we might wanna implement the factory alongwith it.
                    if (!deliveryOptions.equalsIgnoreCase("Store pickup only.")) {
                        deliveryCost = deliveryCostCalc.getDeliveryCost();
                    }
                    System.out.println("Cart Total : "+totalItemCost);
                    System.out.println("\n--------------------------\nTotal Order Cost = "+(deliveryCost+totalItemCost));
                    OrderWrapper ow  = new OrderWrapper(customerID, cartNum, deliveryOptions);
                    orderwrapper.add(ow);
                    Order oo = new Order();
                    oo.OrderDetail(orderwrapper);
                    
                }
                else {
                    System.out.println("Error moving things into cart");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return orderwrapper;
    }
}
