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


class OrderPackedState implements IOrderState {
    @Override
    public void updateOrder(OrderContext context, List<UpdateWrapper> orderStatus, String delivery_type) {
         System.out.println("Order is Packed");
         context.sendUpdate(orderStatus, "Packed");
        if (!delivery_type.equals("Store pickup only")){
            context.setState(new DeliveredState());

        } else { System.out.println("Order has Prescribed Medicines. So please collect the order from the pharmacy");}

        context.updateOrder(orderStatus, delivery_type);
    }
}