package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.StaffDTO;

import java.util.List;

/**
 * IStaffService is an interface for managing staff members in the application.
 * It provides methods for retrieving, inserting, updating, and deleting staff records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IStaffService {

    /**
     * Retrieves a staff member by their number.
     *
     * @param staffNo The unique number of the staff member to retrieve.
     * @return StaffDTO object corresponding to the staff member with the provided number.
     */
    StaffDTO getStaffByNumber(int staffNo);

    /**
     * Retrieves all staff members.
     *
     * @return List of all StaffDTO objects.
     */
    List<StaffDTO> getAllStaff();

    /**
     * Adds a new staff member to the database.
     *
     * @param staff The StaffDTO object representing the new staff member to be added.
     * @return The added StaffDTO object.
     */
    StaffDTO insertStaff(StaffDTO staff);

    /**
     * Updates an existing staff member in the database.
     *
     * @param staff The StaffDTO object representing the staff member to be updated.
     * @return The updated StaffDTO object.
     */
    StaffDTO updateStaff(StaffDTO staff);

    /**
     * Deletes a staff member from the database.
     *
     * @param staffNo The unique number of the staff member to delete.
     * @return The deleted StaffDTO object.
     */
    StaffDTO deleteStaff(int staffNo);
}
