package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.CampingDTO;

import java.util.List;

public interface ICampingService {

    // Retrieves a camping by its code.
    CampingDTO getCampingByCode(String campCode);

    // Retrieves all campings.
    List<CampingDTO> getAllCampings();

    // Adds a new camping to the database.
    CampingDTO insertCamping(CampingDTO camping);

    // Updates an existing camping in the database.
    CampingDTO updateCamping(CampingDTO camping);

    // Deletes a camping from the database.
    CampingDTO deleteCamping(String campCode);
}
