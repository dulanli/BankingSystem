package bankingsystem;

public class Employee extends BankingAccount {      // use of inheritance of BankingAccount class

    private String employee_username;               // encapsulate variables
    private String employee_email;

    public Employee() {                             // default constructor
        this.employee_username = " ";
        this.employee_email = " ";

    }

    public Employee(String name, String pw, String username, String email) {            // 2nd constructor 
        super(name, pw);
        this.employee_username = " ";
        this.employee_email = " ";

    }

    public String getEmployee_username() {          // setters getters
        return employee_username;
    }

    public void setEmployee_username(String employee_username) {
        this.employee_username = employee_username;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    
    

}
