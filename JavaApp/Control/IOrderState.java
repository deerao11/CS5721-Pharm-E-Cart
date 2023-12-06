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
import Control.OrderContext;

interface IOrderState {
    void updateOrder(OrderContext context, List<UpdateWrapper> orderStatus, String delivery_type);
}