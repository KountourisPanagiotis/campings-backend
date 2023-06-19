package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Staff;

import java.util.List;

/**
 * IStaffDAO is an interface for managing staff members in the database.
 * It provides methods for creating, reading, updating, and deleting staff records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IStaffDAO {
    /**
     * Retrieves a staff member by its number.
     *
     * @param staffNo An integer that represents the staff number.
     * @return Staff object corresponding to the provided staff number.
     */
    Staff getStaffByNumber(int staffNo);

    /**
     * Retrieves all staff members.
     *
     * @return List of all Staff objects from the database.
     */
    List<Staff> getAllStaff();

    /**
     * Adds a new staff member to the database.
     *
     * @param staff A Staff object that needs to be added to the database.
     * @return The Staff object that was added to the database.
     */
    Staff insertStaff(Staff staff);

    /**
     * Updates an existing staff member in the database.
     *
     * @param staff A Staff object that needs to be updated in the database.
     * @return The Staff object that was updated in the database.
     */
    Staff updateStaff(Staff staff);

    /**
     * Deletes a staff member from the database.
     *
     * @param staffNo An integer that represents the staff number of the staff member to be deleted.
     * @return The Staff object that was deleted from the database.
     */
    Staff deleteStaff(int staffNo);
}
