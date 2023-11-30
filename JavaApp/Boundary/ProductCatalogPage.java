package Boundary;

import java.util.*;
import Boundary.ProductRepository;
import Entity.ProductDetail;
import Control.ProductCatalogService;


public class ProductCatalogPage extends Page{
    private String catalogNumber;
    private String productNumber;
    Scanner input = new Scanner(System.in);
    List<ProductDetail> productDetails;

    public ProductCatalogPage(){
        catalogNumber = "";
    }
    
    public void start(){
        System.out.println("\nProduct Catalog");
        drawDivider("*");
        System.out.println();
    }

    public void selectCatalog(){
        
        System.out.println("Select a product catalog by typing the serial number.");
        System.out.print("Your choice: ");
        catalogNumber = input.nextLine();
        System.out.println();
        displayProducts(catalogNumber);
        ProductCatalogService pcs = new ProductCatalogService();
        pcs.selectProducts(productDetails);
    }


    public void displayCatalogList(){

        System.out.println("1) Prescribed Medicine");
        System.out.println("2) Unprescribed Medicine");
        System.out.println();

        selectCatalog();
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
