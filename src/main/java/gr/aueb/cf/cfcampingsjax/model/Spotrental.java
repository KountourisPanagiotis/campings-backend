package gr.aueb.cf.cfcampingsjax.model;
import java.util.Date;

public class Spotrental {
    private int bookCode;
    private String campCode;
    private int empNo;
    private Date startDt;
    private Date endDt;
    private int noPers;

    public Spotrental(){}
    public Spotrental(int bookCode, String campCode, int empNo, Date startDt, Date endDt, int noPers) {
        this.bookCode = bookCode;
        this.campCode = campCode;
        this.empNo = empNo;
        this.startDt = startDt;
        this.endDt = endDt;
        this.noPers = noPers;
    }

    // Copy constructor
    public Spotrental(Spotrental other) {
        this.bookCode = other.bookCode;
        this.campCode = other.campCode;
        this.empNo = other.empNo;
        this.startDt = other.startDt;
        this.endDt = other.endDt;
        this.noPers = other.noPers;
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

    @Override
    public String toString() {
        return "Spotrentals{" +
                "bookCode=" + bookCode +
                ", campCode='" + campCode + '\'' +
                ", empNo=" + empNo +
                ", startDt=" + startDt +
                ", endDt=" + endDt +
                ", noPers=" + noPers +
                '}';
    }
}
