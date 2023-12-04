import java.util.*;
import Boundary.LoginPage;
import Boundary.RegisterPage;
import Boundary.ProductCatalogPage;
import Entity.CustomerDetail;
import Entity.CartWrapper;


public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("Enter R to register and L to login");
        boolean registered = false;
        Scanner input = new Scanner(System.in);
        String confirmMsg = input.nextLine();

        //Register
        if(confirmMsg.equalsIgnoreCase("R") ){
            RegisterPage registerPage = new RegisterPage();
            registerPage.start();
            registered = registerPage.register();
        }


        // Login
        if(confirmMsg.equals("L") || confirmMsg.equals("l") || registered == true){
            LoginPage loginPage = new LoginPage();
            loginPage.start();
            CustomerDetail cd = loginPage.login();
            boolean loggedIn = cd == null ? false : true;
            if (loggedIn) {
                System.out.println("LOGGED IN SUCCESSFULLY");

                // Product-Catalog
                ProductCatalogPage productCatalogPage = new ProductCatalogPage();
                productCatalogPage.start();
                List<CartWrapper> selectedProducts = new ArrayList<>();
                productCatalogPage.displayCatalogList(cd, selectedProducts);
                
                
                }



        }
    }
}