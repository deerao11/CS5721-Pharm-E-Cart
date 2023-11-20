package Entity;


public class ProductDetail{
    private String id;
    private String name;
    private String product_description;
    private double quantity;
    private double price;
    private int category_id;
    public ProductDetail(){}

    public ProductDetail(String product_id, String product_name, String product_description, double quantity,double price,int category_id){
        this.id=product_id;
        this.name=product_name;
        this.product_description=product_description;
        this.quantity=quantity;
        this.price=price;
        this.category_id=category_id;

    }

    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
    public String getDescription(){
        return this.product_description;
    }
    public double getQuantity(){
    return this.quantity;
}
    public double getPrice() {
        return this.price;
    }
    public int getCategory(){
    return this.category_id;}
}


