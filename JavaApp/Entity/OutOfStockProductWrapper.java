package Entity;

import java.util.*;

public class OutOfStockProductWrapper {
    public String productName;
    public String dateOutOfStock;
    public String status;
    public int quantity;
    public double price;
    
    public OutOfStockProductWrapper(){}

    public OutOfStockProductWrapper(String productName, String dateOutOfStock, String status, int quantity, double price){
        this.productName = productName;
        this.dateOutOfStock=dateOutOfStock;
        this.status=status;
        this.quantity=quantity;
        this.price=price;
    }

    public String getProductName(){
        return this.productName;
    }
    public String getDateOutOfStock(){
        return this.dateOutOfStock;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public double getPrice(){
        return this.price;
    }
    public String getStatus(){
        return this.status;
    }
}