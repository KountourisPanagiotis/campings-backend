package gr.aueb.cf.cfcampingsjax.dto;

public class StaffDTO {
    private int staffNo;
    private String staffName;
    private String staffSurname;

    public StaffDTO() {}

    public StaffDTO(int staffNo, String staffName, String staffSurname) {
        this.staffNo = staffNo;
        this.staffName = staffName;
        this.staffSurname = staffSurname;
    }

    public int getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(int staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffSurname() {
        return staffSurname;
    }

    public void setStaffSurname(String staffSurname) {
        this.staffSurname = staffSurname;
    }
}
