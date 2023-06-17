package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Booking;
import java.util.List;

public interface IBookingsDAO {
    // Retrieves a booking by its code.
    Booking getBookingByCode(int bookCode);

    // Retrieves all bookings.
    List<Booking> getAllBookings();

    // Adds a new booking to the database.
    Booking insertBooking(Booking booking);

    // Updates an existing booking in the database.
    Booking updateBooking(Booking booking);

    // Deletes a booking from the database.
    Booking deleteBooking(int bookCode);
}
