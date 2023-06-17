package gr.aueb.cf.cfcampingsjax.model;

import java.util.List;

/**
 * COMPOSITION CLASS
 * This class represents a client transaction.
 * It consists of a new general Booking (book code, date, payment code, customer code, staff number)
 * and a list of Spotrental that each one has
 * (book code, camp code, emplacement number, start date, end date, number of person)
 *
 * @author Kountouris Panagiotis
 */
public class ClientTransaction {
    private Booking booking;
    private List<Spotrental> spotRentals;

    public ClientTransaction() {}

    public ClientTransaction(Booking booking, List<Spotrental> spotRentals) {
        this.booking = booking;
        this.spotRentals = spotRentals;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<Spotrental> getSpotRentals() {
        return spotRentals;
    }

    public void setSpotRentals(List<Spotrental> spotRentals) {
        this.spotRentals = spotRentals;
    }

    @Override
    public String toString() {
        return "ClientTransaction{" +
                "booking=" + booking +
                ", spotRentals=" + spotRentals +
                '}';
    }
}
