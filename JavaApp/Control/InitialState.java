
package Control;

import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import Entity.UpdateWrapper;
import Entity.OrderWrapper;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


class InitialState implements IOrderState {
    @Override
    public void updateOrder(OrderContext context, List<UpdateWrapper> orderStatus, String delivery_type) {
        System.out.println("Enter Y to confirmed the order or N to cancel the order(cancel order is valid for only 2 min after placing the order)");
        Scanner input = new Scanner(System.in);
        String sub = input.nextLine();
       
       String time = orderStatus.get(0).orderTime;
        time = time.split(" ")[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime orderTime = LocalTime.parse(time, formatter);
        LocalTime newTime = orderTime.plusMinutes(2);
        LocalTime currentTime = LocalTime.now();
        
        if(sub.equalsIgnoreCase("Y")) {
            context.setState(new ConfirmedState());
        } else 
        if(sub.equalsIgnoreCase("N")) {
            if(currentTime.isBefore(newTime)) {
            context.setState(new CancelledState());}} 
            else {
            System.out.println("You took more than 2 minutes to cancel the order");
            System.out.println("Order cannot be cancelled");
            //context.setState(new OrderPackedState());
        }
         context.updateOrder(orderStatus, delivery_type);
        }
       
    
}