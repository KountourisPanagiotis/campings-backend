package gr.aueb.cf.cfcampingsjax.model;

public class Emplacement {
    private String campCode;
    private int empNo;
    private String catCode;

    public Emplacement() {}

    public Emplacement(String campCode, int empNo, String catCode) {
        this.campCode = campCode;
        this.empNo = empNo;
        this.catCode = catCode;
    }

    // Copy constructor
    public Emplacement(Emplacement other) {
        this.campCode = other.campCode;
        this.empNo = other.empNo;
        this.catCode = other.catCode;
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

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    @Override
    public String toString() {
        return "Emplacements{" +
                "campCode='" + campCode + '\'' +
                ", empNo=" + empNo +
                ", catCode='" + catCode + '\'' +
                '}';
    }
}
