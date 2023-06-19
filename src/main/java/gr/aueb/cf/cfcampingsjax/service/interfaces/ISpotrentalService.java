package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.SpotrentalDTO;

import java.util.Date;
import java.util.List;

/**
 * ISpotrentalService is an interface for managing spot rentals in the application.
 * It provides methods for retrieving, inserting, updating, and deleting spot rental records.
 *
 * @author Kountouris Panagiotis.
 */
public interface ISpotrentalService {

    /**
     * Retrieves a spot rental by its bookCode, campCode, empNo and startDt.
     *
     * @param bookCode The code of the booking associated with the spot rental.
     * @param campCode The code of the camp associated with the spot rental.
     * @param empNo The employee number associated with the spot rental.
     * @param startDt The start date of the spot rental.
     * @return SpotrentalDTO object corresponding to the spot rental with the provided keys.
     */
    SpotrentalDTO getSpotRentalByKeys(int bookCode, String campCode, int empNo, Date startDt);

    /**
     * Retrieves all spot rentals.
     *
     * @return List of all SpotrentalDTO objects.
     */
    List<SpotrentalDTO> getAllSpotRentals();

    /**
     * Retrieves all spot rentals with a specific bookCode.
     *
     * @param bookCode The code of the booking to find associated spot rentals.
     * @return List of SpotrentalDTO objects associated with the given bookCode.
     */
    List<SpotrentalDTO> getAllSpotRentalsWithBookCode(int bookCode);

    /**
     * Adds a new spot rental to the database.
     *
     * @param spotRental The SpotrentalDTO object representing the new spot rental to be added.
     * @return The added SpotrentalDTO object.
     */
    SpotrentalDTO insertSpotRental(SpotrentalDTO spotRental);

    /**
     * Updates an existing spot rental in the database.
     *
     * @param spotRental The SpotrentalDTO object representing the spot rental to be updated.
     * @return The updated SpotrentalDTO object.
     */
    SpotrentalDTO updateSpotRental(SpotrentalDTO spotRental);

    /**
     * Deletes a spot rental from the database.
     *
     * @param bookCode The code of the booking associated with the spot rental to be deleted.
     * @param campCode The code of the camp associated with the spot rental to be deleted.
     * @param empNo The employee number associated with the spot rental to be deleted.
     * @param startDt The start date of the spot rental to be deleted.
     * @return The deleted SpotrentalDTO object.
     */
    SpotrentalDTO deleteSpotRental(int bookCode, String campCode, int empNo, Date startDt);
}
