package gr.aueb.cf.cfcampingsjax.model;

public class Camping {
    private String campCode;
    private String campName;
    private int numOfEmp;

    public Camping() {}
    public Camping(String campCode, String campName, int numOfEmp) {
        this.campCode = campCode;
        this.campName = campName;
        this.numOfEmp = numOfEmp;
    }

    // Copy constructor to avoid sending the reference
    public Camping(Camping other) {
        this.campCode = other.campCode;
        this.campName = other.campName;
        this.numOfEmp = other.numOfEmp;
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

    @Override
    public String toString() {
        return "Campings{" +
                "campCode='" + campCode + '\'' +
                ", campName='" + campName + '\'' +
                ", numOfEmp=" + numOfEmp +
                '}';
    }
}
