package gr.aueb.cf.cfcampingsjax.dto;

public class CampingDTO {
    private String campCode;
    private String campName;
    private int numOfEmp;

    public CampingDTO() {}

    public CampingDTO(String campCode, String campName, int numOfEmp) {
        this.campCode = campCode;
        this.campName = campName;
        this.numOfEmp = numOfEmp;
    }

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public int getNumOfEmp() {
        return numOfEmp;
    }

    public void setNumOfEmp(int numOfEmp) {
        this.numOfEmp = numOfEmp;
    }
}
