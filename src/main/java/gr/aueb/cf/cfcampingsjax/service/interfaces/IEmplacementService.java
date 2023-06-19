package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.EmplacementDTO;

import java.util.List;

/**
 * IEmplacementService is an interface for managing emplacements in the application.
 * It provides methods for retrieving, inserting, updating, and deleting emplacement records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IEmplacementService {

    /**
     * Retrieves an emplacement by its campCode and empNo.
     *
     * @param campCode The code of the camp to which the emplacement belongs.
     * @param empNo The number of the emplacement within the camp.
     * @return EmplacementDTO object corresponding to the emplacement with the provided campCode and empNo.
     */
    EmplacementDTO getEmplacementByCampCodeAndEmpNo(String campCode, int empNo);

    /**
     * Retrieves all emplacements.
     *
     * @return List of all EmplacementDTO objects.
     */
    List<EmplacementDTO> getAllEmplacements();

    /**
     * Adds a new emplacement to the database.
     *
     * @param emplacement The EmplacementDTO object representing the new emplacement to be added.
     * @return The added EmplacementDTO object.
     */
    EmplacementDTO insertEmplacement(EmplacementDTO emplacement);

    /**
     * Updates an existing emplacement in the database.
     *
     * @param emplacement The EmplacementDTO object representing the emplacement to be updated.
     * @return The updated EmplacementDTO object.
     */
    EmplacementDTO updateEmplacement(EmplacementDTO emplacement);

    /**
     * Deletes an emplacement from the database.
     *
     * @param campCode The code of the camp from which the emplacement will be deleted.
     * @param empNo The number of the emplacement to delete within the camp.
     * @return The deleted EmplacementDTO object.
     */
    EmplacementDTO deleteEmplacement(String campCode, int empNo);
}
