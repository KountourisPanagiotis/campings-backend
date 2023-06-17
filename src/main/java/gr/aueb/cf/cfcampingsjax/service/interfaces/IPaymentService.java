package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.PaymentDTO;

import java.util.List;

public interface IPaymentService {

    // Retrieves a payment by its code.
    PaymentDTO getPaymentByCode(int payCode);

    // Retrieves all payments.
    List<PaymentDTO> getAllPayments();

    // Adds a new payment to the database.
    PaymentDTO insertPayment(PaymentDTO payment);

    // Updates an existing payment in the database.
    PaymentDTO updatePayment(PaymentDTO payment);

    // Deletes a payment from the database.
    PaymentDTO deletePayment(int payCode);
}
