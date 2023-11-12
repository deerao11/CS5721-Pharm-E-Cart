package Boundary;

import java.util.Scanner;
import Control.UserAuthenticationControl;

public class RegisterPage extends Page{
    private String fName, lName, emailId, username, password, ppsn, address, eircode;

    public void start(){
        System.out.println("\nRegister Page");
        drawDivider("*");
    }

    //UI for Register page
    public void displayRegisterForm(){
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlease input your details");

        System.out.print("First Name: ");
        fName = input.nextLine();
        
        System.out.print("Last Name: ");
        lName = input.nextLine();
        
        System.out.print("Email ID: ");
        emailId = input.nextLine();

        System.out.print("Username: ");
        username = input.nextLine();

        System.out.print("Password: ");
        password = input.nextLine();

        System.out.print("PPSN No: ");
        ppsn = input.nextLine();

        System.out.print("Address: ");
        address = input.nextLine();

        System.out.print("EirCode: ");
        eircode = input.nextLine();
        
        System.out.println();
    }

    public boolean register(){

        displayRegisterForm();

        UserAuthenticationControl userAuthenticationControl = new UserAuthenticationControl(username, password);

        if(!(userAuthenticationControl.register(fName, lName, username, password, ppsn, address, eircode, emailId))){
            registrationFail();
            return false;
        }
        return true;

    }

    public void registrationFail(){
        System.out.println("\nRegistration failed.");
    }


}
