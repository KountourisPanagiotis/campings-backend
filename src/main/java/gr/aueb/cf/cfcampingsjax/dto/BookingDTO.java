package gr.aueb.cf.cfcampingsjax.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.aueb.cf.cfcampingsjax.service.util.SqlDateDeserializer;
import gr.aueb.cf.cfcampingsjax.service.util.SqlDateSerializer;

import java.util.Date;

public class BookingDTO {
    private int bookCode;

    // Serializer because the Date class is not serializable.
    @JsonSerialize(using = SqlDateSerializer.class)
    @JsonDeserialize(using = SqlDateDeserializer.class)
    private Date bookDt;
    private int payCode;
    private int custCode;
    private int staffNo;

    public BookingDTO() {}

    public BookingDTO(int bookCode, Date bookDt, int payCode, int custCode, int staffNo) {
        this.bookCode = bookCode;
        this.bookDt = bookDt;
        this.payCode = payCode;
        this.custCode = custCode;
        this.staffNo = staffNo;
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
        return "BookingDTO{" +
                "bookCode=" + bookCode +
                ", bookDt=" + bookDt +
                ", payCode=" + payCode +
                ", custCode=" + custCode +
                ", staffNo=" + staffNo +
                '}';
    }
}
