package Boundary;

import java.util.*;
import Boundary.ProductRepository;
import Entity.ProductDetail;
import Control.ProductCatalogService;
import Entity.CustomerDetail;
import Entity.CartWrapper;


public class ProductCatalogPage extends Page{
    private String catalogNumber;
    private String productNumber;
    Scanner input = new Scanner(System.in);
    List<ProductDetail> productDetails;
    public String deliveryOptions ="";

    public ProductCatalogPage(){
        catalogNumber = "";
    }
    
    public void start(){
        System.out.println("\nProduct Catalog");
        drawDivider("*");
        System.out.println();
    }

    public void selectCatalog(CustomerDetail custDetail,  List<CartWrapper> selectedProducts){
        
        System.out.println("Select a product catalog by typing the serial number.");
        System.out.print("Your choice: ");
        catalogNumber = input.nextLine();
        System.out.println();
        switch(catalogNumber) {
            case "1":
            case "2":
                displayProducts(catalogNumber, selectedProducts);
                break;
            default:
                System.out.println("You have entered an invalid input");
                selectCatalog(custDetail, selectedProducts);
        }
        ProductCatalogService pcs = new ProductCatalogService();
        pcs.deliveryOptions = deliveryOptions;
        pcs.selectProducts(productDetails, custDetail, selectedProducts);
    }


    public void displayCatalogList(CustomerDetail CustDetail, List<CartWrapper> selectedProducts){

        System.out.println("1) Prescribed Medicine");
        System.out.println("2) Unprescribed Medicine");
        System.out.println();

        selectCatalog(CustDetail, selectedProducts);
    }

    public void displayProducts(String catalogNumber, List<CartWrapper> selectedProducts) {
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
