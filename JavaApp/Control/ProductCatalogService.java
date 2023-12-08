package Control;

import java.util.*;
import Boundary.ProductCatalogPage;
import Entity.ProductDetail;
import Control.OrderCheckout;
import Entity.CustomerDetail;
import Entity.CartWrapper;
import Entity.OrderWrapper;


public class ProductCatalogService {
    List<ProductDetail> productDetails;
    List<CartWrapper> selectedProducts = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    public String deliveryOptions = "Home Delivery and Store Pickup.";
    Map<String,Integer> prodMap = new HashMap<>();
    public static final String choiceQuestion = "Your Choice: ";

    /*
     * This method is called when the user has selected the category from the catalog and 
     * now has to select the product he would like to order.
     */
    public void selectProducts(List<ProductDetail> prodDetails, CustomerDetail custDetail, List<CartWrapper> selectedProductsParam) {
        productDetails = prodDetails;
        selectedProducts = selectedProductsParam;
        for(int i = 0; i < productDetails.size(); i++) {
            prodMap.put(productDetails.get(i).getId(), i);
            if (productDetails.get(i).getCategory() == 1){
                deliveryOptions = "Store pickup only.";
            }
        }
        System.out.println();
        System.out.println("Enter the ID of the product to order or enter 0 to go back to a different category.");
        System.out.print(choiceQuestion);
        String productID = input.nextLine();
        if(productID.equals("0")){
            ProductCatalogPage pcp = new ProductCatalogPage();
            pcp.deliveryOptions = deliveryOptions;
            pcp.displayCatalogList(custDetail, selectedProducts);
        } else {
            requestQuantity(prodMap.get(productID), custDetail);
            displayNeedMoreProducts(custDetail);
        }
    }

    /*
     * This method is called when the user has selected some products and the system prompts 
     * if they owuld like to select more products.
     */
    public void displayNeedMoreProducts(CustomerDetail custDetail) {
        System.out.println();
        System.out.println("Enter Y if you want to add more products or N if you are done adding products");
        System.out.print(choiceQuestion);
        String needMoreProducts = input.nextLine();
        if (needMoreProducts.equals("y") || needMoreProducts.equals("Y")) {
                selectProducts(productDetails, custDetail, selectedProducts);
            } else if (needMoreProducts.equals("n") || needMoreProducts.equals("N")) {
                displayCheckoutQuestion(custDetail);
            } else {
                displayNeedMoreProducts(custDetail);
            }
    }

    /*
     * This method is called when the user has selected some products and system prompts if they
     * would like to checkout to the cart.
     */
    public void displayCheckoutQuestion(CustomerDetail custDetail) {
        System.out.println();
        System.out.println("Enter Y if you want to checkout or N if you want to add more products");
        System.out.print(choiceQuestion);
        String checkout = input.nextLine();
        if (checkout.equals("y") || checkout.equals("Y")) {
            System.out.println("Checking out");
            OrderCheckout oc = new OrderCheckout();
            List<OrderWrapper> ow = oc.checkout(selectedProducts, custDetail, deliveryOptions);                
        } else if (checkout.equals("n") || checkout.equals("N")){
            selectProducts(productDetails, custDetail, selectedProducts);
        } else {
            displayCheckoutQuestion(custDetail);
        }
    }
    
    /*
     * This method is called when the user has selected a product and the system would
     * like to know the quantity of the products which the customer would like to order.
     */
    public void requestQuantity(int productID, CustomerDetail custDetail) {
        int existingQuantity = (int)productDetails.get(productID).quantity;
        String productName = productDetails.get(productID).name;
        System.out.println();
        System.out.println("Enter the quantity of " +productName+ " you would like to order");
        System.out.print(choiceQuestion);
        int quantity = input.nextInt();
        input.nextLine();
        if (existingQuantity >= quantity) {
            CartWrapper cw = new CartWrapper();
            cw.categoryId = productDetails.get(productID).category_id;
            cw.prodId = productDetails.get(productID).id;
            cw.custId = custDetail.custId;
            cw.quantity = quantity;
            cw.price = productDetails.get(productID).price;
            selectedProducts.add(cw);
        } else {
            System.out.println();
            System.out.println("Current available stock for this medicines is only "+existingQuantity);
            requestQuantity(productID, custDetail);
        }
    }

}
