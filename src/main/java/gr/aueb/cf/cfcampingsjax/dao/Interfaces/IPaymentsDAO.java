package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Payment;
import java.util.List;

public interface IPaymentsDAO {

    // Retrieves a payment by its code.
    Payment getPaymentByCode(int payCode);

    // Retrieves all payments.
    List<Payment> getAllPayments();

    // Adds a new payment to the database.
    Payment insertPayment(Payment payment);

    // Updates an existing payment in the database.
    Payment updatePayment(Payment payment);

    // Deletes a payment from the database.
    Payment deletePayment(int payCode);
}
