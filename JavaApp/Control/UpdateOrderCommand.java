package Control;

import Entity.UpdateWrapper;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class  UpdateOrderCommand implements Command {

    private UpdateOrder order;
    private List<UpdateWrapper> cw;
    private String updateStatus;

    public UpdateOrderCommand(UpdateOrder order,List<UpdateWrapper> cw, String updateStatus) {
        this.order = order;
        this.cw=cw;
        this.updateStatus=updateStatus;
    }

    @Override
    public void execute() {
         this.order.updateOrder(this.cw,this.updateStatus);
    }
}