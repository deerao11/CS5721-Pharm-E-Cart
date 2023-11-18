package model;


public class ProductDetail{
    private String id;
    private String name;
    private String product_description;
    private double quantity;

    public ProductDetail(){}

    public ProductDetail(String product_id, String product_name, String product_description, double quantity){
        this.id=product_id;
        this.name=product_name;
        this.product_description=product_description;
        this.quantity=quantity;

    }

    public String getName(){
        return this.name;
    }


}
