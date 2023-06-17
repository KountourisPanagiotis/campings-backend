package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Emplacement;

import java.util.List;

public interface IEmplacementsDAO {
    // Retrieves an emplacement by its campCode and empNo.
    Emplacement getEmplacementByCampCodeAndEmpNo(String campCode, int empNo);

    // Retrieves all emplacements.
    List<Emplacement> getAllEmplacements();

    // Adds a new emplacement to the database.
    Emplacement insertEmplacement(Emplacement emplacement);

    // Updates an existing emplacement in the database.
    Emplacement updateEmplacement(Emplacement emplacement);

    // Deletes an emplacement from the database.
    Emplacement deleteEmplacement(String campCode, int empNo);
}
