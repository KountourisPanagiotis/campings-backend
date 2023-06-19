package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Emplacement;

import java.util.List;

/**
 * IEmplacementsDAO is an interface for managing emplacements in the database.
 * It provides methods for creating, reading, updating, and deleting emplacement records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IEmplacementsDAO {

    /**
     * Retrieves an emplacement by its campCode and empNo.
     *
     * @param campCode A string that represents the camping code.
     * @param empNo An integer that represents the employee number.
     * @return Emplacement object corresponding to the provided campCode and empNo.
     */
    Emplacement getEmplacementByCampCodeAndEmpNo(String campCode, int empNo);

    /**
     * Retrieves all emplacements.
     *
     * @return List of all Emplacement objects from the database.
     */
    List<Emplacement> getAllEmplacements();

    /**
     * Adds a new emplacement to the database.
     *
     * @param emplacement An Emplacement object that needs to be added to the database.
     * @return The Emplacement object that was added to the database.
     */
    Emplacement insertEmplacement(Emplacement emplacement);

    /**
     * Updates an existing emplacement in the database.
     *
     * @param emplacement An Emplacement object that needs to be updated in the database.
     * @return The Emplacement object that was updated in the database.
     */
    Emplacement updateEmplacement(Emplacement emplacement);

    /**
     * Deletes an emplacement from the database.
     *
     * @param campCode A string that represents the camping code.
     * @param empNo An integer that represents the employee number.
     * @return The Emplacement object that was deleted from the database.
     */
    Emplacement deleteEmplacement(String campCode, int empNo);
}
