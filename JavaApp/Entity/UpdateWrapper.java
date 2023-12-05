package Entity;

public class UpdateWrapper {
	public String orderId;
	public String orderTime;
	public int custId;
	
	public UpdateWrapper() {
		
	}
	
	public UpdateWrapper(String orderId, String orderTime,int custId) {
		this.orderId = orderId;
		this.orderTime = orderTime;
		this.custId=custId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	public String getOrderTime() {
		return this.orderTime;
	}
	public int getCustId(){
		return this.custId;
	}
}