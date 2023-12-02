package Entity;


public class CustomerDetail{
    public int custId;
    public String firstName;
    public String lastName;
    public String email;
    public String role;
    
    public CustomerDetail(){}

    public CustomerDetail(int custId, String firstName, String lastName, String email, String role){
        this.custId=custId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.role=role;
    }

    public int getCustId(){
        return this.custId;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
    return this.email;
}
    public String getRole() {
        return this.role;
    }
}


