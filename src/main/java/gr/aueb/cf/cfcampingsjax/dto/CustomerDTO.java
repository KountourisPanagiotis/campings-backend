package gr.aueb.cf.cfcampingsjax.dto;

public class CustomerDTO {
    private int custCode;
    private String custName;
    private String custSurname;
    private String custPhone;

    public CustomerDTO() {}

    public CustomerDTO(int custCode, String custName, String custSurname, String custPhone) {
        this.custCode = custCode;
        this.custName = custName;
        this.custSurname = custSurname;
        this.custPhone = custPhone;
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
}
