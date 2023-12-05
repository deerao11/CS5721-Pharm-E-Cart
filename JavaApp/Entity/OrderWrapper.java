package Entity;

public class OrderWrapper {
	public int custId;
	public String orderId;
	public String delType;
	
	public OrderWrapper() {
		
	}
	
	public OrderWrapper(int custId, String orderId, String delType) {
		this.custId = custId;
		this.orderId = orderId;
		this.delType = delType;
	}
	
	public int getCustId() {
		return this.custId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	public String getDelType() {
		return this.delType;
	}

}