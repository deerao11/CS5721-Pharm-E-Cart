package Control;

import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.*;
import Entity.OrderDetailWrapper;

public class OrderController implements IObservable {
    public List<OrderDetailWrapper> orderSummary = new ArrayList<>();

    public ArrayList<IObserver> observerList = new ArrayList<IObserver>();
    public void addObs(IObserver obs) {
        observerList.add(obs);
    }

    public void removeObs(IObserver obs) {
        observerList.remove(obs);
    }

    public void notifyObservers(String orderId) {
        System.out.println("In notify observers");
        observerList.forEach(observer -> observer.update(orderId));
    }

    public List<OrderDetailWrapper> getPastOrders(int customerId) {
        try {
            var uri = URI.create("https://falconer2-71714182580c.herokuapp.com/getCustomerOrders");
            String jsonData = "{\"customer_id\":\""+customerId+"\"}";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body().toString());
                int price=0;
                String orderNum="";
                String orderTime;
                String delType;
                String orderStatus;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String currentOrderNum = jsonObject.getString("order_number");
                    OrderDetailWrapper odw = new OrderDetailWrapper();
                    int currentsize = orderSummary.size();
                    if (orderNum.equals(currentOrderNum)) {
                        price += jsonObject.getInt("price");
                        OrderDetailWrapper updatedInstance = orderSummary.get(currentsize-1);
                        updatedInstance.setPrice(price);
                    }
                    else {
                        price = jsonObject.getInt("price");
                        orderNum = currentOrderNum;
                        orderTime = jsonObject.getString("datetime");
                        delType = jsonObject.getString("delivery_type");
                        orderStatus = jsonObject.getString("order_status");
                        odw = new OrderDetailWrapper(price, orderNum, orderTime, delType, orderStatus);
                        orderSummary.add(odw);
                    }
                }
                return orderSummary;
            }
            else
            return null;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
