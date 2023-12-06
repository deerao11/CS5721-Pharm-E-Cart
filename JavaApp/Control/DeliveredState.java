package Control;

import java.net.URI;
import java.util.List;
import org.json.JSONObject;
import Entity.UpdateWrapper;
import Entity.OrderWrapper;

class DeliveredState implements IOrderState {
    @Override
    public void updateOrder(OrderContext context, List<UpdateWrapper> orderStatus, String delivery_type ) {
        if (!delivery_type.equals("Store pickup only")){
		System.out.println("Order out for delivery");
		context.sendUpdate(orderStatus, "Delivered");
		 }
        
    }
}