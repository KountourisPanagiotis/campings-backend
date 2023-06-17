package gr.aueb.cf.cfcampingsjax.service.util;

import gr.aueb.cf.cfcampingsjax.dto.BookingDTO;
import gr.aueb.cf.cfcampingsjax.dto.ClientTransactionDTO;
import gr.aueb.cf.cfcampingsjax.dto.SpotrentalDTO;
import gr.aueb.cf.cfcampingsjax.model.Booking;
import gr.aueb.cf.cfcampingsjax.model.ClientTransaction;
import gr.aueb.cf.cfcampingsjax.model.Spotrental;

import java.util.List;
import java.util.stream.Collectors;

public class DTOUtil {

    public static ClientTransaction convertToModel(ClientTransactionDTO clientTransactionDTO){
        if (clientTransactionDTO == null) {
            return null;
        }
        List<Spotrental> spotRentals = clientTransactionDTO.getSpotRentals().stream().map(DTOUtil::convertToModel).collect(Collectors.toList());
        return new ClientTransaction(DTOUtil.convertToModel(clientTransactionDTO.getBooking()), spotRentals);
    }

    public static ClientTransactionDTO convertToDTO(ClientTransaction clientTransaction){
        if (clientTransaction == null) {
            return null;
        }
        List<SpotrentalDTO> spotRentalDTOs = clientTransaction.getSpotRentals().stream().map(DTOUtil::convertToDTO).collect(Collectors.toList());
        return new ClientTransactionDTO(DTOUtil.convertToDTO(clientTransaction.getBooking()), spotRentalDTOs);
    }

    public static Booking convertToModel(BookingDTO bookingDTO){
        if (bookingDTO == null) {
            return null;
        }
        return new Booking(bookingDTO.getBookCode(),
                bookingDTO.getBookDt(), bookingDTO.getPayCode(),
                bookingDTO.getCustCode(), bookingDTO.getStaffNo());
    };

    public static BookingDTO convertToDTO(Booking booking){
        if (booking == null) {
            return null;
        }
        return new BookingDTO(booking.getBookCode(), booking.getBookDt(),
                booking.getPayCode(), booking.getCustCode(), booking.getStaffNo());
    };

    public static SpotrentalDTO convertToDTO(Spotrental spotrental) {
        if (spotrental == null) {
            return null;
        }

        return new SpotrentalDTO(spotrental.getBookCode(), spotrental.getCampCode(), spotrental.getEmpNo(), spotrental.getStartDt(), spotrental.getEndDt(), spotrental.getNoPers());
    }

    public static Spotrental convertToModel(SpotrentalDTO spotrentalDTO) {
        if (spotrentalDTO == null) {
            return null;
        }

        return new Spotrental(spotrentalDTO.getBookCode(), spotrentalDTO.getCampCode(), spotrentalDTO.getEmpNo(), spotrentalDTO.getStartDt(), spotrentalDTO.getEndDt(), spotrentalDTO.getNoPers());
    }
}
