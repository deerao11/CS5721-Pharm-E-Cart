import java.util.List;
import java.util.Scanner;
import Boundary.LoginPage;
import Boundary.ProductFetcher;
import Boundary.RegisterPage;
import Boundary.ProductCatalogPage;
import model.ProductDetail;

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
            boolean loggedIn = loginPage.login();
            if (loggedIn) {
                System.out.println("LOGGED IN SUCCESSFULLY");

                // Product-Catalog
                ProductCatalogPage productCatalogPage = new ProductCatalogPage();
                productCatalogPage.start();
                productCatalogPage.displayCatalogList();
                String catalogNumber = productCatalogPage.selectCatalog();


                ProductFetcher productFetcher = new ProductFetcher();
                List<ProductDetail> productDetails = productFetcher.fetchData(catalogNumber);
                System.out.println(String.format("Available Products for catalog number %s : \n",catalogNumber ));
                productDetails.forEach(productDetail -> {
                    System.out.println("Product name: " + productDetail.getName() +
                            ", Product description: " + productDetail.getDescription() +
                            ", Quantity: " + productDetail.getQuantity() +
                            ", Price: " + productDetail.getPrice() +
                            ", Category ID: " + productDetail.getCategory());

                });

            }



        }
    }
}
