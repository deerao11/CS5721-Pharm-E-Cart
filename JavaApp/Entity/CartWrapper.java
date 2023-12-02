package Entity;

import java.util.*;

public class CartWrapper {
    public int categoryId;
    public int custId;
    public String prodId;
    public int quantity;
    public double price;
    
    public CartWrapper(){}

    public CartWrapper(int categoryId, int custId, String prodId, int quantity, double price){
        this.categoryId = categoryId;
        this.custId=custId;
        this.prodId=prodId;
        this.quantity=quantity;
        this.price=price;
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
}