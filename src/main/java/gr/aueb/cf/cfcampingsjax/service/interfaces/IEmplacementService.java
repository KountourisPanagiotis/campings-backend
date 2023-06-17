package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.EmplacementDTO;

import java.util.List;

public interface IEmplacementService {

    // Retrieves an emplacement by its campCode and empNo.
    EmplacementDTO getEmplacementByCampCodeAndEmpNo(String campCode, int empNo);

    // Retrieves all emplacements.
    List<EmplacementDTO> getAllEmplacements();

    // Adds a new emplacement to the database.
    EmplacementDTO insertEmplacement(EmplacementDTO emplacement);

    // Updates an existing emplacement in the database.
    EmplacementDTO updateEmplacement(EmplacementDTO emplacement);

    // Deletes an emplacement from the database.
    EmplacementDTO deleteEmplacement(String campCode, int empNo);
}
