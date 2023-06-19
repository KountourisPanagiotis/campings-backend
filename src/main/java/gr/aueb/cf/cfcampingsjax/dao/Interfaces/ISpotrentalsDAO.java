package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Spotrental;

import java.util.List;
import java.util.Date;

/**
 * ISpotrentalsDAO is an interface for managing spot rentals in the database.
 * It provides methods for creating, reading, updating, and deleting spot rental records.
 *
 * @author Kountouris Panagiotis.
 */
public interface ISpotrentalsDAO {
    /**
     * Retrieves a spot rental by its bookCode, campCode, empNo, and startDt.
     *
     * @param bookCode An integer that represents the booking code.
     * @param campCode A string that represents the camping code.
     * @param empNo An integer that represents the employee number.
     * @param startDt A Date object that represents the start date of the rental.
     * @return Spotrental object corresponding to the provided keys.
     */
    Spotrental getSpotrentalByPrimaryKeys(int bookCode, String campCode, int empNo, Date startDt);

    /**
     * Retrieves all spot rentals.
     *
     * @return List of all Spotrental objects from the database.
     */
    List<Spotrental> getAllSpotrentals();

    /**
     * Retrieves all spot rentals with a specific bookCode.
     *
     * @param bookCode An integer that represents the booking code.
     * @return List of Spotrental objects that have the specified bookCode.
     */
    List<Spotrental> getAllSpotrentalsWithBookCode(int bookCode);

    /**
     * Adds a new spot rental to the database.
     *
     * @param spotrental A Spotrental object that needs to be added to the database.
     * @return The Spotrental object that was added to the database.
     */
    Spotrental insertSpotrental(Spotrental spotrental);

    /**
     * Updates an existing spot rental in the database.
     *
     * @param spotrental A Spotrental object that needs to be updated in the database.
     * @return The Spotrental object that was updated in the database.
     */
    Spotrental updateSpotrental(Spotrental spotrental);

    /**
     * Deletes a spot rental from the database.
     *
     * @param bookCode An integer that represents the booking code.
     * @param campCode A string that represents the camping code.
     * @param empNo An integer that represents the employee number.
     * @param startDt A Date object that represents the start date of the rental.
     * @return The Spotrental object that was deleted from the database.
     */
    Spotrental deleteSpotrental(int bookCode, String campCode, int empNo, Date startDt);
}
