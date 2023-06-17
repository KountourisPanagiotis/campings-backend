package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Spotrental;
import java.util.List;
import java.util.Date;

public interface ISpotrentalsDAO {
    // Retrieves a spot rental by its bookCode, campCode, empNo, and startDt.
    Spotrental getSpotrentalByPrimaryKeys(int bookCode, String campCode, int empNo, Date startDt);

    // Retrieves all spot rentals.
    List<Spotrental> getAllSpotrentals();

    // Retrieves all spot rentals with a specific bookCode.
    List<Spotrental> getAllSpotrentalsWithBookCode(int bookCode);

    // Adds a new spot rental to the database.
    Spotrental insertSpotrental(Spotrental spotrental);

    // Updates an existing spot rental in the database.
    Spotrental updateSpotrental(Spotrental spotrental);

    // Deletes a spot rental from the database.
    Spotrental deleteSpotrental(int bookCode, String campCode, int empNo, Date startDt);
}
