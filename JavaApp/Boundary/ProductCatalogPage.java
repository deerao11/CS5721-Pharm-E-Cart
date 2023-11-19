package Boundary;

import java.util.Scanner;


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

    public String selectCatalog(){

        Scanner input = new Scanner(System.in);
        
        System.out.println("Select a product catalog by typing the serial number.");
        System.out.print("Your choice: ");
        catalogNumber = input.nextLine();
            
        System.out.println();
        return catalogNumber;
    }


    public void displayCatalogList(){

        System.out.println("1) Prescribed Medicine");
        System.out.println("2) Unprescribed Medicine");
        System.out.println();

        // selectCatalog(catalogNumber);
    }


}
