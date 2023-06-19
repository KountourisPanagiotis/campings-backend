package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Booking;
import java.util.List;

/**
 * IBookingsDAO is an interface for managing bookings in the database.
 * It provides the necessary methods for creating, reading, updating, and deleting booking records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IBookingsDAO {
    /**
     * Retrieves a booking by its code.
     *
     * @param bookCode An integer that represents the booking code.
     * @return Booking object corresponding to the provided booking code.
     */
    Booking getBookingByCode(int bookCode);

    /**
     * Retrieves all bookings.
     *
     * @return List of all Booking objects from the database.
     */
    List<Booking> getAllBookings();

    /**
     * Adds a new booking to the database.
     *
     * @param booking A Booking object that needs to be added to the database.
     * @return The Booking object that was added to the database.
     */
    Booking insertBooking(Booking booking);

    /**
     * Updates an existing booking in the database.
     *
     * @param booking A Booking object that needs to be updated in the database.
     * @return The Booking object that was updated in the database.
     */
    Booking updateBooking(Booking booking);

    /**
     * Deletes a booking from the database.
     *
     * @param bookCode An integer that represents the booking code of the booking to be deleted.
     * @return The Booking object that was deleted from the database.
     */
    Booking deleteBooking(int bookCode);
}
