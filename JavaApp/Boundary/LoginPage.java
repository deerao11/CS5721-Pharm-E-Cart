package Boundary;

import java.util.Scanner;
import java.io.Console;
import Control.UserAuthenticationControl;
import Boundary.Page;
import Entity.CustomerDetail;

public class LoginPage extends Page {
    private String userId;
    private String password;

    public void start(){
        System.out.println("\nLogin Page");
        drawDivider("*");
    }

    //UI for Login
    public void displayLoginForm(){
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlease input your user ID and password.");

        System.out.print("User ID: ");
        userId = input.nextLine();

        char[] hiddenPassword = System.console().readPassword("Password: ");
        password = String.valueOf(hiddenPassword);
        
        System.out.println();
    }

    public CustomerDetail login(){

        displayLoginForm();

        UserAuthenticationControl userAuthenticationControl = new UserAuthenticationControl(
            userId, password
        );

        CustomerDetail cd = userAuthenticationControl.authenticate();
        if( cd == null){
            loginFail();
            return null;
        } else {
            return cd;
        }
    }

    public void loginFail(){
        System.out.println("\nLogin fail... Please try another account or password.");
    }


}
