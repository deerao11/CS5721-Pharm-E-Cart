package Control;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Boundary.ProductCatalogPage;
import Entity.ProductDetail;
import Control.OrderCheckout;
import Entity.CartWrapper;
import Entity.CustomerDetail;
import Entity.OrderWrapper;
import Entity.UpdateWrapper;

public class Order {
	CustomerDetail cd;
	List<OrderWrapper> orderwraper = new ArrayList<>();
	List<CartWrapper> selectedProducts = new ArrayList<>();
	Scanner input = new Scanner(System.in);
	
	public void OrderDetail(List<OrderWrapper> orderWrapper, List<CartWrapper> selectedProductsParam) {
		orderwraper = orderWrapper;
		//displayCartDetails(selectedProductsParam);
		System.out.println("Enter Y if you want to place the order or Enter N to end the session");
		String confirm = input.nextLine();
		if(confirm.equals("Y") || confirm.equals("y")) {
			PlaceOrder po = new PlaceOrder();
			List<UpdateWrapper> up = po.placeOrder(orderWrapper);
			
		}
	}
	public void displayCartDetails(List<CartWrapper> cw) {
        //    System.out.println("products in cart :" );
		// for(int i=0; i < cw.size(); i++) {
        //     jsonData += "{\"category_id\":\""+cw.get(i).categoryId+"\",\"product_id\":\""+cw.get(i).prodId+"\",\"customer_id\":\""+cw.get(i).custId+"\",\"quantity\":\""+cw.get(i).quantity+"\",\"price\":\""+cw.get(i).price+"\"}";
        //     if (i < cw.size() - 1) {
        //         jsonData +=  ",";
        //     } else {
        //         jsonData +=  "]";
        //     }
        // }
	}

    public void updateOrder(List<UpdateWrapper> orderStatus) {
		String status = "";
		System.out.println("Enter Y to ship order or N to cancel order");
		String sub = input.nextLine();
		if(sub.equals("Y")||sub.equals("y")) {
			status = "Confirmed";
			System.out.println("shipped successfully");
		}else if(sub.equals("n")||sub.equals("N")) {
			String time = orderStatus.get(0).orderTime;
			time = time.split(" ")[1];
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalTime orderTime = LocalTime.parse(time, formatter);
			LocalTime newTime = orderTime.plusMinutes(2);
			LocalTime currentTime = LocalTime.now();
			if(currentTime.isBefore(newTime)) {
			status = "Cancelled";
			}else {
				status = "Confirmed";
				System.out.println("You took more than 2 minutes to cancel the order");
				System.out.println("Order cannnot be cancelled");
				System.out.println("Order automatically moved to shipped state");
			}
		}
		 
		UpdateOrder uo = new UpdateOrder();
		String UpdtOrd = uo.updateOrder(orderStatus, status);
	}
}

