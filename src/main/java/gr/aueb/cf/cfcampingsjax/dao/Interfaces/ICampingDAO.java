package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Camping;

import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * ICampingDAO is an interface for managing campings in the database.
 * It provides the necessary methods for creating, reading, updating, and deleting camping records.
 *
 * @author Kountouris Panagiotis.
 */
public interface ICampingDAO {
    /**
     * Retrieves a camping by its code.
     *
     * @param campCode A String that represents the camping code.
     * @return Camping object corresponding to the provided camping code.
     */
    Camping getCampingByCode(String campCode);

    /**
     * Retrieves all campings.
     *
     * @return List of all Camping objects from the database.
     */
    List<Camping> getAllCampings();

    /**
     * Adds a new camping to the database.
     *
     * @param camping A Camping object that needs to be added to the database.
     * @return The Camping object that was added to the database.
     */
    Camping insertCamping(Camping camping);

    /**
     * Updates an existing camping in the database.
     *
     * @param camping A Camping object that needs to be updated in the database.
     * @return The Camping object that was updated in the database.
     */
    Camping updateCamping(Camping camping);

    /**
     * Deletes a camping from the database.
     *
     * @param campCode A String that represents the camping code of the camping to be deleted.
     * @return The Camping object that was deleted from the database.
     */
    Camping deleteCamping(String campCode);
}
