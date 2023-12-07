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
	
	public void OrderDetail(List<OrderWrapper> orderWrapper) {
		orderwraper = orderWrapper;
		System.out.println("Enter Y if you want to place the order or Enter N to end the session");
		String confirm = input.nextLine();
		if(confirm.equals("Y") || confirm.equals("y")) {
			PlaceOrder po = new PlaceOrder();
			List<UpdateWrapper> up = po.placeOrder(orderWrapper);
			
		}
	}
}

