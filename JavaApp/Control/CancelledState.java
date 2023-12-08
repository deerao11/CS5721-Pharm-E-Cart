package Control;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import Entity.OrderWrapper;
import Entity.UpdateWrapper;


class CancelledState implements IOrderState {
    //@Override
    public void updateOrder(OrderContext context, List<UpdateWrapper> orderStatus, String delivery_type) {
        
        System.out.println("Order Cancelled successfully");
        context.sendUpdate(orderStatus, "Cancelled");
        
    }
}