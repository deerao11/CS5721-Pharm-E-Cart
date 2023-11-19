import java.util.List;
import java.util.Scanner;
import Boundary.LoginPage;
import Boundary.ProductFetcher;
import Boundary.RegisterPage;
import model.ProductDetail;

//1. deploy python code
//2. refactor java codevv, remove unwated imports/variable/classes/functions etc etc
//3. show product catalog after login

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
            if (loggedIn == true) {
                System.out.println("LOGGED IN SUCCESSFULLY");
                ProductFetcher productFetcher = new ProductFetcher();
                List<ProductDetail> productDetails = productFetcher.fetchdata();
                System.out.println("Available Products : \n");
                productDetails.forEach(productDetail -> {
                    System.out.println("Product name: " + productDetail.getName() +
                            ", Product description: " + productDetail.getDescription() +
                            ", Quantity: " + productDetail.getQuantity() +
                            ", Price: " + productDetail.getPrice() +
                            ", Category ID: " + productDetail.getCategory());

//                     Uncomment the following code if you want to fetch additional data
//                     ProductFetcher productFetcher = new ProductFetcher();
//                     List<ProductDetail> productList = productFetcher.fetchdata();
//                     System.out.println("Product size: " + productList.size());
                });
        }

        
    }
}}
