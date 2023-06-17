package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Staff;

import java.util.List;

public interface IStaffDAO {
    // Retrieves a staff member by its number.
    Staff getStaffByNumber(int staffNo);

    // Retrieves all staff members.
    List<Staff> getAllStaff();

    // Adds a new staff member to the database.
    Staff insertStaff(Staff staff);

    // Updates an existing staff member in the database.
    Staff updateStaff(Staff staff);

    // Deletes a staff member from the database.
    Staff deleteStaff(int staffNo);
}
