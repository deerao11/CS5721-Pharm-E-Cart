package Boundary;

import java.util.*;
import Boundary.ProductRepository;
import Entity.ProductDetail;
import Control.ProductCatalogService;
import Entity.CustomerDetail;
import Entity.CartWrapper;
import Control.OrderController;
import Entity.OrderDetailWrapper;
import Control.PharmacyController;
import Entity.OutOfStockProductWrapper;


public class ProductCatalogPage extends Page{
    private String catalogNumber;
    Scanner input = new Scanner(System.in);
    List<ProductDetail> productDetails;
    public static final String choiceQuestion = "Your Choice: ";
    public String deliveryOptions ="Home Delivery and Store Pickup.";

    public ProductCatalogPage(){
        catalogNumber = "";
    }
    @Override
    public void start(){
        System.out.println("\nProduct Catalog");
        drawDivider("*");
        System.out.println();
    }

    public void selectCatalog(CustomerDetail custDetail,  List<CartWrapper> selectedProducts){
        if (custDetail.role.equals("Customer")) {
            System.out.println("Select a product catalog by typing the serial number.");
            System.out.print(choiceQuestion);
            catalogNumber = input.nextLine();
            System.out.println();
            switch(catalogNumber) {
                case "1":
                case "2":
                    displayProducts(catalogNumber);
                    ProductCatalogService pcs = new ProductCatalogService();
                    pcs.deliveryOptions = deliveryOptions;
                    pcs.selectProducts(productDetails, custDetail, selectedProducts);
                    break;
                case "3":
                    OrderController oc = new OrderController();
                    List<OrderDetailWrapper> orderSummary = oc.getPastOrders(custDetail.custId);
                    System.out.println("Here is a list of your past orders");
                    drawDivider("*");
                    orderSummary.forEach(orderDetail -> {
                    System.out.println("Order ID: " + orderDetail.getOrderNum());
                    System.out.println("Order Time: " + orderDetail.getOrderTime());
                    System.out.println("Order price: " + orderDetail.getPrice());
                    System.out.println("Order Delivery type: " + orderDetail.getDelType());
                    System.out.println("Order Status: " + orderDetail.getOrderStatus());
                    System.out.println();
                    drawDivider("*");
                    });
                    selectCatalog(custDetail, selectedProducts);
                    break;
                default:
                    System.out.println("You have entered an invalid input");
                    selectCatalog(custDetail, selectedProducts);
            }
        } else {
            System.out.println("Select your choice from the menu.");
            System.out.print(choiceQuestion);
            catalogNumber = input.nextLine();
            switch(catalogNumber) {
                case "1":
                    PharmacyController pc = new PharmacyController();
                    List<OutOfStockProductWrapper> oosProdList = pc.showInventoryOutOfStock();
                    if(oosProdList.size() > 0){
                        System.out.println();
                        System.out.println("These are the products that have been requested from the vendor.");
                        drawDivider("*");
                        oosProdList.forEach(oosProd -> {
                            System.out.println("Product Name: " + oosProd.getProductName());
                            System.out.println("Quantity Requested: " + oosProd.getQuantity());
                            System.out.println("Product price: " + oosProd.getPrice());
                            System.out.println("Requested Time: " + oosProd.getDateOutOfStock());
                            System.out.println("Order Status: " + oosProd.getStatus());
                            System.out.println();

                        });
                        System.out.println("Select Y if you would like to confirm receival of these products or N to exit");
                        System.out.print(choiceQuestion);
                        String confirmation = input.nextLine();
                        System.out.println();
                        if(confirmation.equalsIgnoreCase("y") ){
                            pc.vendorUpdateInventory(custDetail);
                        }
                    } else {
                        System.out.println("All products in stock! No requests raised so far.");
                    }
                    
                    break;
                case "2":
                    System.out.println("You have selected to trigger a notification for a packed order.");
                    break;
                default:
                    System.out.println("You have entered an invalid input");
                    selectCatalog(custDetail, selectedProducts);
            }
        }
        
    }


    public void displayCatalogList(CustomerDetail custDetail, List<CartWrapper> selectedProducts){
        switch (custDetail.role) {
            case "Customer":
                System.out.println("1) Prescribed Medicine");
                System.out.println("2) Unprescribed Medicine");
                System.out.println("3) View Order History");
                System.out.println();
                break;
            case "Pharmacist":
                System.out.println("1) Update Inventory");
                System.out.println("2) Order Packed Notification");
                System.out.println();   
                break; 
            default:
                System.out.println("You are not authorized to use this system.");
                break;        
        }
        
        selectCatalog(custDetail, selectedProducts);
    }

    public void displayProducts(String catalogNumber) {
        ProductRepository productFetcher = new ProductRepository();
        productDetails = productFetcher.fetchData(catalogNumber);
        System.out.println(String.format("Available Products for catalog number %s : \n",catalogNumber ));
                productDetails.forEach(productDetail -> {
                    System.out.println("Product ID: " + productDetail.getId() +
                            ", Product name: " + productDetail.getName() +
                            ", Product quantity: " + productDetail.getQuantity() +
                            ", Product description: " + productDetail.getDescription() +
                            ", Price: " + productDetail.getPrice());

                });
    }
}
