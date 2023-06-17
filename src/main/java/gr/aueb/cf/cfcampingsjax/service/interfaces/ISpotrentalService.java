package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.SpotrentalDTO;

import java.util.Date;
import java.util.List;

public interface ISpotrentalService {
    // Retrieves a spot rental by its bookCode, campCode, empNo and startDt.
    SpotrentalDTO getSpotRentalByKeys(int bookCode, String campCode, int empNo, Date startDt);

    // Retrieves all spot rentals.
    List<SpotrentalDTO> getAllSpotRentals();

    // Retrieves all spot rentals with a specific bookCode.
    List<SpotrentalDTO> getAllSpotRentalsWithBookCode(int bookCode);

    // Adds a new spot rental to the database.
    SpotrentalDTO insertSpotRental(SpotrentalDTO spotRental);

    // Updates an existing spot rental in the database.
    SpotrentalDTO updateSpotRental(SpotrentalDTO spotRental);

    // Deletes a spot rental from the database.
    SpotrentalDTO deleteSpotRental(int bookCode, String campCode, int empNo, Date startDt);
}