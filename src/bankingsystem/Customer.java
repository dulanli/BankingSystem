package bankingsystem;

public class Customer extends BankingAccount{        // use of inheritance    

    private int customer_accountno;                 // encapsulate variables
    private double customer_balance;
    private double customer_salary;

    public Customer() {                             // default constructor
        this.customer_accountno = 0;
        this.customer_balance = 0.0;
        this.customer_salary = 0.0;

    }

    public Customer(int no, String name, String pw, double bal, double sal) {            // 2nd constructor 
        
        super(name, pw);                // super from BankingAccount class
        this.customer_accountno = no;
        this.customer_balance = bal;
        this.customer_salary = sal;

    }

    public int getCustomer_accountno() {            // setters getters
        return customer_accountno;
    }

    public void setCustomer_accountno(int customer_accountno) {
        this.customer_accountno = customer_accountno;
    }

    public double getCustomer_balance() {
        return customer_balance;
    }

    public void setCustomer_balance(double customer_balance) {
        this.customer_balance = customer_balance;
    }

    public double getCustomer_salary() {
        return customer_salary;
    }

    public void setCustomer_salary(double customer_salary) {
        this.customer_salary = customer_salary;
    }


}
