package bankingsystem;


public class detailsExpenses {                     

    private String expdate;                 // encapsulate variables
    private String expdetails;
    private double expamount;
    
    public detailsExpenses() {              // default constructor
        this.expdate = "";
        this.expdetails = "";
        this.expamount = 0.0;

    }
    
    public detailsExpenses(String date, String details, double amt) {            // 2nd constructor 
        this.expdate = date;
        this.expdetails = details;
        this.expamount = amt;
        
    }

    public String getExpdate() {            // setters and getters
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getExpdetails() {
        return expdetails;
    }

    public void setExpdetails(String expdetails) {
        this.expdetails = expdetails;
    }

    public double getExpamount() {
        return expamount;
    }

    public void setExpamount(double expamount) {
        this.expamount = expamount;
    }

}
