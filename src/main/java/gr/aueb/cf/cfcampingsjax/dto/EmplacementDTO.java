package gr.aueb.cf.cfcampingsjax.dto;

public class EmplacementDTO {
    private String campCode;
    private int empNo;
    private String catCode;

    public EmplacementDTO() {}

    public EmplacementDTO(String campCode, int empNo, String catCode) {
        this.campCode = campCode;
        this.empNo = empNo;
        this.catCode = catCode;
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
}
