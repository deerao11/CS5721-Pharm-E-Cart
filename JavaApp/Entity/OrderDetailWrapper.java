package Entity;

import java.time.*;

public class OrderDetailWrapper {
	public int price;
	public String orderNum;
    public String orderTime;
	public String delType;
	public String orderStatus;

	public OrderDetailWrapper() {

	}

	public OrderDetailWrapper(int price, String orderNum, String orderTime, String delType, String orderStatus) {
		this.price = price;
		this.orderNum = orderNum;
        this.orderTime = orderTime;
		this.delType = delType;
		this.orderStatus = orderStatus;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public int getPrice() {
		return this.price;
	}

    public String getOrderTime() {
        return this.orderTime;
    }

	public String getDelType() {
		return this.delType;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}