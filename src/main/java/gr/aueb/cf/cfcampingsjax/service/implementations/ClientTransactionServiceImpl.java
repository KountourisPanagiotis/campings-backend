package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IBookingsDAO;
import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IClientTransactionsDAO;
import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ISpotrentalsDAO;
import gr.aueb.cf.cfcampingsjax.dto.BookingDTO;
import gr.aueb.cf.cfcampingsjax.dto.ClientTransactionDTO;
import gr.aueb.cf.cfcampingsjax.dto.SpotrentalDTO;
import gr.aueb.cf.cfcampingsjax.model.Booking;
import gr.aueb.cf.cfcampingsjax.model.ClientTransaction;
import gr.aueb.cf.cfcampingsjax.model.Spotrental;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IClientTransactionService;
import gr.aueb.cf.cfcampingsjax.service.util.DBUtil;
import gr.aueb.cf.cfcampingsjax.service.util.DTOUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientTransactionServiceImpl implements IClientTransactionService {

    private final IBookingsDAO bookingsDAO;
    private final ISpotrentalsDAO spotrentalsDAO;
    private final IClientTransactionsDAO clientTransactionsDAO;

    public ClientTransactionServiceImpl(IBookingsDAO bookingsDAO, ISpotrentalsDAO spotrentalsDAO, IClientTransactionsDAO clientTransactionsDAO) {
        this.bookingsDAO = bookingsDAO;
        this.spotrentalsDAO = spotrentalsDAO;
        this.clientTransactionsDAO = clientTransactionsDAO;
    }

    @Override
    public ClientTransactionDTO getClientTransactionByBookCode(int bookCode) {
        Booking booking = bookingsDAO.getBookingByCode(bookCode);
        if (booking == null) {
            return null;
        }

        List<Spotrental> spotrentals = spotrentalsDAO.getAllSpotrentalsWithBookCode(bookCode);
        if (spotrentals == null) {
            return null;
        }

        return convertToClientTransactionDTO(booking, spotrentals);
    }

    @Override
    public List<ClientTransactionDTO> getAllClientTransactions() {
        List<ClientTransaction> clientTransactions = clientTransactionsDAO.getAllClientTransactions();

        // Convert List<ClientTransaction> to List<ClientTransactionDTO>
        List<ClientTransactionDTO> clientTransactionDTOs = new ArrayList<>();

        for (ClientTransaction clientTransaction : clientTransactions) {
            clientTransactionDTOs.add(DTOUtil.convertToDTO(clientTransaction));
        }

        return clientTransactionDTOs;
    }

    @Override
    public ClientTransactionDTO insertClientTransaction(ClientTransactionDTO clientTransaction) {
        if (clientTransaction.getBooking() == null || clientTransaction.getSpotRentals() == null) {
            return null;
        }

        Booking booking = DTOUtil.convertToModel(clientTransaction.getBooking());
        List<Spotrental> spotrentals = new ArrayList<>();

        for (SpotrentalDTO spotrentalDTO : clientTransaction.getSpotRentals()) {
            Spotrental spotrental = DTOUtil.convertToModel(spotrentalDTO);
            spotrentals.add(spotrental);
        }

        Connection conn = null;
        try {
            // Get a connection
            conn = DBUtil.getConnection();

            // Set auto-commit to false in order to start the transaction
            conn.setAutoCommit(false);

            // Insert booking and spotrentals into the database
            // We need to insert the booking first because the spotrentals
            // will have a foreign key to the booking.
            booking = bookingsDAO.insertBooking(booking);
            for (Spotrental spotrental : spotrentals) {
                spotrentalsDAO.insertSpotrental(spotrental);
            }

            // Commit the transaction if everything is successful
            conn.commit();

            return convertToClientTransactionDTO(booking, spotrentals);
        } catch (SQLException | ClassNotFoundException e) {
            // Rollback the transaction if an exception occurs
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        } finally {
            // Set auto-commit back to true and close the connection
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public ClientTransactionDTO updateClientTransaction(ClientTransactionDTO clientTransaction) {
        if (clientTransaction.getBooking() == null || clientTransaction.getSpotRentals() == null) {
            return null;
        }

        Booking booking = DTOUtil.convertToModel(clientTransaction.getBooking());
        List<Spotrental> spotrentals = new ArrayList<>();

        for (SpotrentalDTO spotrentalDTO : clientTransaction.getSpotRentals()) {
            Spotrental spotrental = DTOUtil.convertToModel(spotrentalDTO);
            spotrentals.add(spotrental);
        }

        Connection conn = null;
        try {
            // Get a connection
            conn = DBUtil.getConnection();

            // Set auto-commit to false in order to start the transaction
            conn.setAutoCommit(false);

            // Update booking and spotrentals in the database
            booking = bookingsDAO.updateBooking(booking);
            for (Spotrental spotrental : spotrentals) {
                spotrentalsDAO.updateSpotrental(spotrental);
            }

            // Commit the transaction if everything is successful
            conn.commit();

            return convertToClientTransactionDTO(booking, spotrentals);
        } catch (SQLException | ClassNotFoundException e) {
            // Rollback the transaction if an exception occurs
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        } finally {
            // Set auto-commit back to true and close the connection
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ClientTransactionDTO deleteClientTransaction(int bookCode) {
        Connection conn = null;
        try {
            // Get a connection
            conn = DBUtil.getConnection();

            // Set auto-commit to false in order to start the transaction
            conn.setAutoCommit(false);

            // Fetch the booking and spotrentals
            Booking booking = bookingsDAO.getBookingByCode(bookCode);
            if (booking == null) {
                return null;
            }
            List<Spotrental> spotrentals = spotrentalsDAO.getAllSpotrentalsWithBookCode(bookCode);
            if (spotrentals == null) {
                return null;
            }

            // Delete booking and spotrentals from the database
            bookingsDAO.deleteBooking(bookCode);
            for (Spotrental spotrental : spotrentals) {
                spotrentalsDAO.deleteSpotrental(spotrental.getBookCode(), spotrental.getCampCode(), spotrental.getEmpNo(), spotrental.getStartDt());
            }

            // Commit the transaction if everything is successful
            conn.commit();

            return convertToClientTransactionDTO(booking, spotrentals);
        } catch (SQLException | ClassNotFoundException e) {
            // Rollback the transaction if an exception occurs
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        } finally {
            // Set auto-commit back to true and close the connection
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private ClientTransactionDTO convertToClientTransactionDTO(Booking booking, List<Spotrental> spotrentals) {
        if (booking == null || spotrentals == null || spotrentals.isEmpty()) {
            return null; // Return null if booking or spotrentals are null or empty
        }

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookCode(booking.getBookCode());
        bookingDTO.setBookDt(booking.getBookDt());
        bookingDTO.setPayCode(booking.getPayCode());
        bookingDTO.setCustCode(booking.getCustCode());
        bookingDTO.setStaffNo(booking.getStaffNo());

        List<SpotrentalDTO> spotrentalDTOs = new ArrayList<>();
        for (Spotrental spotrental : spotrentals) {
            SpotrentalDTO spotrentalDTO = new SpotrentalDTO();
            spotrentalDTO.setBookCode(spotrental.getBookCode());
            spotrentalDTO.setCampCode(spotrental.getCampCode());
            spotrentalDTO.setEmpNo(spotrental.getEmpNo());
            spotrentalDTO.setStartDt(spotrental.getStartDt());
            spotrentalDTO.setEndDt(spotrental.getEndDt());
            spotrentalDTO.setNoPers(spotrental.getNoPers());

            spotrentalDTOs.add(spotrentalDTO);
        }

        return new ClientTransactionDTO(bookingDTO, spotrentalDTOs);
    }

}
