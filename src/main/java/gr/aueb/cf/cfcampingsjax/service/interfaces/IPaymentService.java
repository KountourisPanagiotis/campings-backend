package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.PaymentDTO;

import java.util.List;

/**
 * IPaymentService is an interface for managing payments in the application.
 * It provides methods for retrieving, inserting, updating, and deleting payment records.
 *
 * @author Kountouris Panagiotis.
 */
public interface IPaymentService {

    /**
     * Retrieves a payment by its code.
     *
     * @param payCode The unique code of the payment to retrieve.
     * @return PaymentDTO object corresponding to the payment with the provided code.
     */
    PaymentDTO getPaymentByCode(int payCode);

    /**
     * Retrieves all payments.
     *
     * @return List of all PaymentDTO objects.
     */
    List<PaymentDTO> getAllPayments();

    /**
     * Adds a new payment to the database.
     *
     * @param payment The PaymentDTO object representing the new payment to be added.
     * @return The added PaymentDTO object.
     */
    PaymentDTO insertPayment(PaymentDTO payment);

    /**
     * Updates an existing payment in the database.
     *
     * @param payment The PaymentDTO object representing the payment to be updated.
     * @return The updated PaymentDTO object.
     */
    PaymentDTO updatePayment(PaymentDTO payment);

    /**
     * Deletes a payment from the database.
     *
     * @param payCode The unique code of the payment to delete.
     * @return The deleted PaymentDTO object.
     */
    PaymentDTO deletePayment(int payCode);
}
