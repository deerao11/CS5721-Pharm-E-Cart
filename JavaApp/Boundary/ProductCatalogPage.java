package Boundary;

import java.util.*;
import Boundary.ProductRepository;
import Entity.ProductDetail;


public class ProductCatalogPage extends Page{
    private String catalogNumber;
    private String productNumber;
    Scanner input = new Scanner(System.in);
    private String productsAdded="";
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
        selectProducts();
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

    public void selectProducts() {
        System.out.println();
        System.out.println("Enter the ID of the product to order or enter 0 to go back to a different category.");
        System.out.print("Your choice: ");
        int productID = input.nextInt();
        input.nextLine();
        if(productID == 0){
            displayCatalogList();
        } else {
            requestQuantity(productID - 1);
            displayNeedMoreProducts();
        }
    }

    public void displayNeedMoreProducts() {
        System.out.println();
        System.out.println("Enter Y if you want to add more products or N if you are done adding products");
        System.out.print("Your choice: ");
        String needMoreProducts = input.nextLine();
        if (needMoreProducts.equals("y") || needMoreProducts.equals("Y")) {
                selectProducts();
            } else if (needMoreProducts.equals("n") || needMoreProducts.equals("N")) {
                displayCheckoutQuestion();
            } else {
                displayNeedMoreProducts();
            }
    }

    public void displayCheckoutQuestion() {
        System.out.println();
        System.out.println("Enter Y if you want to checkout or N if you want to add more products");
        System.out.print("Your choice: ");
        String checkout = input.nextLine();
        if (checkout.equals("y") || checkout.equals("Y")) {
            System.out.println("Checking out");                
        } else if (checkout.equals("n") || checkout.equals("N")){
            selectProducts();
        } else {
            displayCheckoutQuestion();
        }
    }

    public void requestQuantity(int productID) {
        int existingQuantity = (int)productDetails.get(productID).quantity;
        String productName = productDetails.get(productID).name;
        System.out.println();
        System.out.println("Enter the quantity of " +productName+ " you would like to order");
        System.out.print("Your choice: ");
        int quantity = input.nextInt();
        input.nextLine();
        if (existingQuantity > quantity) {
            productsAdded += productID+":"+quantity+";";
        } else {
            System.out.println();
            System.out.println("Current available stock for this medicines is only "+existingQuantity);
            requestQuantity(productID);
        }
    }


}
