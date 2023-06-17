package gr.aueb.cf.cfcampingsjax.model;

public class Payment {
    private int payCode;
    private String payMethod;

    public Payment() {}
    public Payment(int payCode, String payMethod) {
        this.payCode = payCode;
        this.payMethod = payMethod;
    }

    // Copy constructor
    public Payment(Payment other) {
        this.payCode = other.payCode;
        this.payMethod = other.payMethod;
    }

    public int getPayCode() {
        return payCode;
    }

    public void setPayCode(int payCode) {
        this.payCode = payCode;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "payCode=" + payCode +
                ", payMethod='" + payMethod + '\'' +
                '}';
    }
}
