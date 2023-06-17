package gr.aueb.cf.cfcampingsjax.dto;

public class PaymentDTO {
    private int payCode;
    private String payMethod;

    public PaymentDTO() {}

    public PaymentDTO(int payCode, String payMethod) {
        this.payCode = payCode;
        this.payMethod = payMethod;
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
}
