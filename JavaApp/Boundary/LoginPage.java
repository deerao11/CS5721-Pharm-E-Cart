package Boundary;

import java.util.Scanner;
import Control.UserAuthenticationControl;

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

        System.out.print("Password: ");
        password = input.nextLine();
        
        System.out.println();
    }

    public boolean login(){

        displayLoginForm();

        UserAuthenticationControl userAuthenticationControl = new UserAuthenticationControl(
            userId, password
        );

        if(!(userAuthenticationControl.authenticate())){
            loginFail();
            return false;
        }
        return true;
    }

    public void loginFail(){
        System.out.println("\nLogin fail... Please try another account or password.");
    }


}
