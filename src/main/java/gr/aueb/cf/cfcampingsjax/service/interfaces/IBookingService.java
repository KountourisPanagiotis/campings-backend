package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.BookingDTO;
import gr.aueb.cf.cfcampingsjax.model.Booking;

import java.util.List;

public interface IBookingService {
    // Retrieves a booking by its code.
    BookingDTO getBookingByCode(int bookCode);

    // Retrieves all bookings.
    List<BookingDTO> getAllBookings();

    // Adds a new booking to the database.
    BookingDTO insertBooking(BookingDTO booking);

    // Updates an existing booking in the database.
    BookingDTO updateBooking(BookingDTO booking);

    // Deletes a booking from the database.
    BookingDTO deleteBooking(int bookCode);
}
