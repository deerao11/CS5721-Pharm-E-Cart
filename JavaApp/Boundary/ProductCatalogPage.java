package Boundary;

import java.util.Scanner;
import java.util.Vector;

public class ProductCatalogPage extends Page{
    private String catalogNumber;
    private String productNumber;

    public ProductCatalogPage(){
        catalogNumber = "";
    }
    
    public void start(){
        System.out.println("\nProduct Catalog");
        drawDivider("*");
        System.out.println();
    }

    public void selectCatalog(){

        Scanner input = new Scanner(System.in);
        
        System.out.println("Select a product catalog by typing the serial number.");
        System.out.print("Your choice: ");
        catalogNumber = input.nextLine();
            
        System.out.println();
    }


    public void displayCatalogList(){

        System.out.println("001) prescribed medicine");
        System.out.println("002) unprescribed medicine");
        System.out.println();

        // selectCatalog(catalogNumber);
    }


}
