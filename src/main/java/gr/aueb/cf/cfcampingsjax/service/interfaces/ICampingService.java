package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.CampingDTO;
import java.util.List;

/**
 * ICampingService is an interface for managing campings in the application.
 * It provides methods for retrieving, inserting, updating, and deleting camping records.
 *
 * @author Kountouris Panagiotis.
 */
public interface ICampingService {

    /**
     * Retrieves a camping by its code.
     *
     * @param campCode The unique code of the camping to retrieve.
     * @return CampingDTO object corresponding to the camping with the provided code.
     */
    CampingDTO getCampingByCode(String campCode);

    /**
     * Retrieves all campings.
     *
     * @return List of all CampingDTO objects.
     */
    List<CampingDTO> getAllCampings();

    /**
     * Adds a new camping to the database.
     *
     * @param camping The CampingDTO object representing the new camping to be added.
     * @return The added CampingDTO object.
     */
    CampingDTO insertCamping(CampingDTO camping);

    /**
     * Updates an existing camping in the database.
     *
     * @param camping The CampingDTO object representing the camping to be updated.
     * @return The updated CampingDTO object.
     */
    CampingDTO updateCamping(CampingDTO camping);

    /**
     * Deletes a camping from the database.
     *
     * @param campCode The unique code of the camping to delete.
     * @return The deleted CampingDTO object.
     */
    CampingDTO deleteCamping(String campCode);
}
