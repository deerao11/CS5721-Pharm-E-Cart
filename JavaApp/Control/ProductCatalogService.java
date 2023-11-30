package Control;

import java.util.*;
import Boundary.ProductCatalogPage;
import Entity.ProductDetail;


public class ProductCatalogService {
    private String productsAdded="";
    List<ProductDetail> productDetails;
    Scanner input = new Scanner(System.in);

    public void selectProducts(List<ProductDetail> prodDetails) {
        productDetails = prodDetails;
        System.out.println();
        System.out.println("Enter the ID of the product to order or enter 0 to go back to a different category.");
        System.out.print("Your choice: ");
        int productID = input.nextInt();
        input.nextLine();
        if(productID == 0){
            ProductCatalogPage pcp = new ProductCatalogPage();
            pcp.displayCatalogList();
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
                selectProducts(productDetails);
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
            selectProducts(productDetails);
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
