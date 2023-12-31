package Control;

import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import org.json.JSONObject;
import org.json.JSONArray;
import Entity.CustomerDetail;
import Entity.OutOfStockProductWrapper;

public class PharmacyController implements IObserver {
    public String baseURL = "https://falconer2-71714182580c.herokuapp.com/";
    List<OutOfStockProductWrapper> oosProdList = new ArrayList<>();

    public void update(String orderId) {
        manageInventory(orderId);
    }

    /*
     * This method is used to update the inventory after a specific order has
     * been cancelled i.e any products in this order are added back to the inventory.
     */
    public void manageInventory(String orderId) {
        try {
            var uri = URI.create(baseURL+"updateInventory");
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
        } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * This method is used to display the inventory that is about to be out of stock
     * from the inventory, for which requests have been raised to the vendor. It also
     * displays the status of the request. This option is only available to the Pharmacist.
     */
    public List<OutOfStockProductWrapper> showInventoryOutOfStock() {
        try {
            var uri = URI.create(baseURL+"getInventoryProducts");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body().toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    double price = jsonObject.getDouble("price");
                    int quantity = jsonObject.getInt("quantity");
                    String datetime = jsonObject.getString("datetime");
                    String status = jsonObject.getString("order_status");
                    String prodName = jsonObject.getString("product_name");
                    OutOfStockProductWrapper odw = new OutOfStockProductWrapper(prodName, datetime, status, quantity, price);
                    oosProdList.add(odw);
                }
                return oosProdList;
            }
            else
                return null;
        } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 

    /*
     * This method is used by the Pharmacist to confirm receival of the products
     * which were sent by the vendor. It calls the API to update the status of the
     * request which was created to "Delivered"
     */
    public void vendorUpdateInventory(CustomerDetail custDetail) {
        try {
            var uri = URI.create(baseURL+"updateInventoryPharmacists");
            String jsonData = "{\"pharmacist_id\":\""+custDetail.custId+"\"}";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Inventory updated from vendor.");
            } else {
                System.out.println("Error occurred during inventory update. ");
            }
        } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}