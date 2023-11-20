import java.util.Scanner;
import Boundary.LoginPage;
import Boundary.RegisterPage;
import Boundary.ProductCatalogPage;

public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("Enter R to register and L to login");
        boolean registered = false;
        Scanner input = new Scanner(System.in);
        String confirmMsg = input.nextLine();

        //Register
        if(confirmMsg.equals("R") || confirmMsg.equals("r")){
            RegisterPage registerPage = new RegisterPage();
            registerPage.start();
            registered = registerPage.register();
        }

        // Login
        if(confirmMsg.equals("L") || confirmMsg.equals("l") || registered == true){
            LoginPage loginPage = new LoginPage();
            loginPage.start();
            boolean loggedIn = loginPage.login();
            if (loggedIn == true) {
                System.out.println("LOGGED IN SUCCESSFULLY");
                // Product-Catalog
                ProductCatalogPage productCatalogPage = new ProductCatalogPage();
                productCatalogPage.start();
                productCatalogPage.displayCatalogList();
                productCatalogPage.selectCatalog();
            }
        
        
        
        }
    }
}
