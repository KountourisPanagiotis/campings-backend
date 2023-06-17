package gr.aueb.cf.cfcampingsjax.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.aueb.cf.cfcampingsjax.service.util.SqlDateDeserializer;
import gr.aueb.cf.cfcampingsjax.service.util.SqlDateSerializer;

import java.util.Date;

public class SpotrentalDTO {
    private int bookCode;
    private String campCode;
    private int empNo;

    // Serializer because the Date class is not serializable.
    @JsonSerialize(using = SqlDateSerializer.class)
    @JsonDeserialize(using = SqlDateDeserializer.class)
    private Date startDt;

    @JsonSerialize(using = SqlDateSerializer.class)
    @JsonDeserialize(using = SqlDateDeserializer.class)
    private Date endDt;
    private int noPers;

    public SpotrentalDTO() {}

    public SpotrentalDTO(int bookCode, String campCode, int empNo, Date startDt, Date endDt, int noPers) {
        this.bookCode = bookCode;
        this.campCode = campCode;
        this.empNo = empNo;
        this.startDt = startDt;
        this.endDt = endDt;
        this.noPers = noPers;
    }

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public int getNoPers() {
        return noPers;
    }

    public void setNoPers(int noPers) {
        this.noPers = noPers;
    }
}
