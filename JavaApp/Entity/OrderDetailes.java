package Entity;

import java.util.*;

public class CartWrapper {
    public String orderId;
    public int categoryId;
    public int custId;
    public String prodId;
    public int quantity;
    public double price;
     public String delType;

    
    public CartWrapper(){}

    public CartWrapper(Sint categoryId, int custId, String prodId, int quantity, double price){
        this.orderId=orderId;
        this.categoryId = categoryId;
        this.custId=custId;
        this.prodId=prodId;
        this.quantity=quantity;
        this.price=price;
        this.delType=delType;
    }

    public int getCustId(){
        return this.custId;
    }
    public String getProdId(){
        return this.prodId;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public double getPrice(){
        return this.price;
    }
    public int getCategoryId(){
        return this.categoryId;
    }
    public String getOderId(){
        return this.orderId;
    }
    public int getDelType(){
        return this.delType;
    }
}