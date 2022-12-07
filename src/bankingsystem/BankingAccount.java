package bankingsystem;

public class BankingAccount {                        // encapsulate variables

    private String person_name;
    private String person_password;

    public BankingAccount() {                          // default constructor
        this.person_name = " ";
        this.person_password = " ";

    }

    public BankingAccount(String name, String pw) {   // 2nd constructor 

        this.person_name = name;
        this.person_password = pw;

    }

    public String getperson_name() {                // setters and getters
        return person_name;
    }

    public void setperson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getperson_password() {
        return person_password;
    }

    public void setperson_password(String person_password) {
        this.person_password = person_password;
    }

    
    
}
