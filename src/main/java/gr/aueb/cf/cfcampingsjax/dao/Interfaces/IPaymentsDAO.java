package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Payment;
import java.util.List;

/**
 * IPaymentsDAO is an interface for managing payments in the database.
 * It provides methods for creating, reading, updating, and deleting payment records.
 *
 * @author Kountouris Panagiotis
 */
public interface IPaymentsDAO {

    /**
     * Retrieves a payment by its code.
     *
     * @param payCode An integer that represents the payment code.
     * @return Payment object corresponding to the provided payment code.
     */
    Payment getPaymentByCode(int payCode);

    /**
     * Retrieves all payments.
     *
     * @return List of all Payment objects from the database.
     */
    List<Payment> getAllPayments();

    /**
     * Adds a new payment to the database.
     *
     * @param payment A Payment object that needs to be added to the database.
     * @return The Payment object that was added to the database.
     */
    Payment insertPayment(Payment payment);

    /**
     * Updates an existing payment in the database.
     *
     * @param payment A Payment object that needs to be updated in the database.
     * @return The Payment object that was updated in the database.
     */
    Payment updatePayment(Payment payment);

    /**
     * Deletes a payment from the database.
     *
     * @param payCode An integer that represents the payment code of the payment to be deleted.
     * @return The Payment object that was deleted from the database.
     */
    Payment deletePayment(int payCode);
}
