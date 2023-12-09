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

//context class
class OrderContext {
    private IOrderState state;
    private UpdateOrder updateOrder;
    public OrderContext() {
        state = new InitialState();
        updateOrder = new UpdateOrder();
    }
    public void setState(IOrderState state) {
        this.state = state;
    }
    public void updateOrder(List<UpdateWrapper> orderStatus, String delivery_type) {
        state.updateOrder(this, orderStatus, delivery_type);
    }
    /**
     * command pattern implementation
     * @param cw
     * @param updateStatus
     */
    public void sendUpdate(List<UpdateWrapper> cw, String updateStatus) {
        UpdateOrderCommand uo = new UpdateOrderCommand(updateOrder,cw, updateStatus);
        uo.execute();
    }
}

