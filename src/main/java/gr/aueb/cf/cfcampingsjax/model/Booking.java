package gr.aueb.cf.cfcampingsjax.model;

import java.util.Date;

public class Booking {
    private int bookCode;
    private Date bookDt;
    private int payCode;
    private int custCode;
    private int staffNo;

    public Booking() {}

    public Booking(int bookCode, Date bookDt, int payCode, int custCode, int staffNo) {
        this.bookCode = bookCode;
        this.bookDt = bookDt;
        this.payCode = payCode;
        this.custCode = custCode;
        this.staffNo = staffNo;
    }

    // Copy Constructor
    public Booking(Booking other) {
        this.bookCode = other.bookCode;
        this.bookDt = other.bookDt;
        this.payCode = other.payCode;
        this.custCode = other.custCode;
        this.staffNo = other.staffNo;
    }

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    public Date getBookDt() {
        return bookDt;
    }

    public void setBookDt(Date bookDt) {
        this.bookDt = bookDt;
    }

    public int getPayCode() {
        return payCode;
    }

    public void setPayCode(int payCode) {
        this.payCode = payCode;
    }

    public int getCustCode() {
        return custCode;
    }

    public void setCustCode(int custCode) {
        this.custCode = custCode;
    }

    public int getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(int staffNo) {
        this.staffNo = staffNo;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookCode=" + bookCode +
                ", bookDt=" + bookDt +
                ", payCode=" + payCode +
                ", custCode=" + custCode +
                ", staffNo=" + staffNo +
                '}';
    }
}
