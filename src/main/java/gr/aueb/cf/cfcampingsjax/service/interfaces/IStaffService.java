package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.StaffDTO;

import java.util.List;

public interface IStaffService {

    // Retrieves a staff member by their number.
    StaffDTO getStaffByNumber(int staffNo);

    // Retrieves all staff members.
    List<StaffDTO> getAllStaff();

    // Adds a new staff member to the database.
    StaffDTO insertStaff(StaffDTO staff);

    // Updates an existing staff member in the database.
    StaffDTO updateStaff(StaffDTO staff);

    // Deletes a staff member from the database.
    StaffDTO deleteStaff(int staffNo);
}
