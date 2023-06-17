package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Camping;

import javax.ws.rs.ext.Provider;
import java.util.List;

public interface ICampingDAO {
    // Retrieves a camping by its code.
    Camping getCampingByCode(String campCode);

    // Retrieves all campings.
    List<Camping> getAllCampings();

    // Adds a new camping to the database.
    Camping insertCamping(Camping camping);

    // Updates an existing camping in the database.
    Camping updateCamping(Camping camping);

    // Deletes a camping from the database.
    Camping deleteCamping(String campCode);
}
