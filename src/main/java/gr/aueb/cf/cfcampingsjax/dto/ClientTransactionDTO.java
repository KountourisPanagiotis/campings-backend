package gr.aueb.cf.cfcampingsjax.dto;

import java.util.List;

/**
 * COMPOSITION CLASS
 * This class represents a client transaction.
 * It consists of a new general bookingDTO(book code, date, payment code,customer code,staff number)
 * and a list of SpotrentalDTO that each one has
 * (book code, camp code, emplacement number, start date, end date, number of person)
 *
 * @author Kountouris Panagiotis
 */
public class ClientTransactionDTO {
    private BookingDTO booking;
    private List<SpotrentalDTO> spotRentals;

    public ClientTransactionDTO() {}

    public ClientTransactionDTO(BookingDTO booking, List<SpotrentalDTO> spotRentals) {
        this.booking = booking;
        this.spotRentals = spotRentals;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public List<SpotrentalDTO> getSpotRentals() {
        return spotRentals;
    }

    public void setSpotRentals(List<SpotrentalDTO> spotRentals) {
        this.spotRentals = spotRentals;
    }

    @Override
    public String toString() {
        return "ClientTransactionDTO{" +
                "booking=" + booking +
                ", spotRentals=" + spotRentals +
                '}';
    }
}
