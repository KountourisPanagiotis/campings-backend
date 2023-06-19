package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.BookingDTO;
import gr.aueb.cf.cfcampingsjax.model.Booking;

import java.util.List;

/**
 * IBookingService is an interface for managing bookings in the application.
 * It provides methods for retrieving, inserting, updating, and deleting booking records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IBookingService {

    /**
     * Retrieves a booking by its code.
     *
     * @param bookCode The unique code of the booking to retrieve.
     * @return BookingDTO object corresponding to the booking with the provided code.
     */
    BookingDTO getBookingByCode(int bookCode);

    /**
     * Retrieves all bookings.
     *
     * @return List of all BookingDTO objects.
     */
    List<BookingDTO> getAllBookings();

    /**
     * Adds a new booking to the database.
     *
     * @param booking The BookingDTO object representing the new booking to be added.
     * @return The added BookingDTO object.
     */
    BookingDTO insertBooking(BookingDTO booking);

    /**
     * Updates an existing booking in the database.
     *
     * @param booking The BookingDTO object representing the booking to be updated.
     * @return The updated BookingDTO object.
     */
    BookingDTO updateBooking(BookingDTO booking);

    /**
     * Deletes a booking from the database.
     *
     * @param bookCode The unique code of the booking to delete.
     * @return The deleted BookingDTO object.
     */
    BookingDTO deleteBooking(int bookCode);
}
