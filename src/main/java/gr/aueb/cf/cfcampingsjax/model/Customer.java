package gr.aueb.cf.cfcampingsjax.model;

public class Customer {
    private int custCode;
    private String custName;
    private String custSurname;
    private String custPhone;

    public Customer() {}

    public Customer(int custCode, String custName, String custSurname, String custPhone) {
        this.custCode = custCode;
        this.custName = custName;
        this.custSurname = custSurname;
        this.custPhone = custPhone;
    }

    // Copy constructor
    public Customer(Customer other) {
        this.custCode = other.custCode;
        this.custName = other.custName;
        this.custSurname = other.custSurname;
        this.custPhone = other.custPhone;
    }

    public int getCustCode() {
        return custCode;
    }

    public void setCustCode(int custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSurname() {
        return custSurname;
    }

    public void setCustSurname(String custSurname) {
        this.custSurname = custSurname;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "custCode=" + custCode +
                ", custName='" + custName + '\'' +
                ", custSurname='" + custSurname + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
