package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IBookingsDAO;
import gr.aueb.cf.cfcampingsjax.dto.BookingDTO;
import gr.aueb.cf.cfcampingsjax.model.Booking;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IBookingService;
import java.util.ArrayList;
import java.util.List;

public class BookingServiceImpl implements IBookingService {

    private final IBookingsDAO bookingsDAO;

    // Dependency Injection
    public BookingServiceImpl(IBookingsDAO bookingsDAO) {
        this.bookingsDAO = bookingsDAO;
    }

    @Override
    public BookingDTO getBookingByCode(int bookCode) {
        Booking booking = bookingsDAO.getBookingByCode(bookCode);
        return convertToDTO(booking);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingsDAO.getAllBookings();
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDTOs.add(convertToDTO(booking));
        }
        return bookingDTOs;
    }

    @Override
    public BookingDTO insertBooking(BookingDTO bookingDTO) {
        Booking booking = convertToModel(bookingDTO);
        booking = bookingsDAO.insertBooking(booking);
        return convertToDTO(booking);
    }

    @Override
    public BookingDTO updateBooking(BookingDTO bookingDTO) {
        Booking booking = convertToModel(bookingDTO);
        booking = bookingsDAO.updateBooking(booking);
        return convertToDTO(booking);
    }

    @Override
    public BookingDTO deleteBooking(int bookCode) {
        Booking booking = bookingsDAO.deleteBooking(bookCode);
        return convertToDTO(booking);
    }

    private BookingDTO convertToDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        return new BookingDTO(booking.getBookCode(), booking.getBookDt(),
                booking.getPayCode(), booking.getCustCode(), booking.getStaffNo());
    }

    private Booking convertToModel(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            return null;
        }
        return new Booking(bookingDTO.getBookCode(),
                bookingDTO.getBookDt(), bookingDTO.getPayCode(),
                bookingDTO.getCustCode(), bookingDTO.getStaffNo());
    }
}
