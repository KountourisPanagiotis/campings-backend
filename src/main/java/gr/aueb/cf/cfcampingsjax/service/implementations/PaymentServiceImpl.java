package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IPaymentsDAO;
import gr.aueb.cf.cfcampingsjax.dto.PaymentDTO;
import gr.aueb.cf.cfcampingsjax.model.Payment;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IPaymentService;

import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements IPaymentService {

    private final IPaymentsDAO paymentsDAO;

    // Dependency Injection
    public PaymentServiceImpl(IPaymentsDAO paymentsDAO) {
        this.paymentsDAO = paymentsDAO;
    }

    @Override
    public PaymentDTO getPaymentByCode(int payCode) {
        Payment payment = paymentsDAO.getPaymentByCode(payCode);
        return convertToDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentsDAO.getAllPayments();
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOs.add(convertToDTO(payment));
        }
        return paymentDTOs;
    }

    @Override
    public PaymentDTO insertPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToModel(paymentDTO);
        if (paymentsDAO.getPaymentByCode(payment.getPayCode()) != null) {
            throw new IllegalArgumentException("Payment with this code already exists.");
        }
        payment = paymentsDAO.insertPayment(payment);
        return convertToDTO(payment);
    }

    @Override
    public PaymentDTO updatePayment(PaymentDTO paymentDTO) {
        Payment payment = convertToModel(paymentDTO);
        if (paymentsDAO.getPaymentByCode(payment.getPayCode()) == null) {
            throw new IllegalArgumentException("No such payment exists to update.");
        }
        payment = paymentsDAO.updatePayment(payment);
        return convertToDTO(payment);
    }

    @Override
    public PaymentDTO deletePayment(int payCode) {
        Payment payment = paymentsDAO.deletePayment(payCode);
        return convertToDTO(payment);
    }

    private PaymentDTO convertToDTO(Payment payment) {
        if (payment == null) {
            return null;
        }
        return new PaymentDTO(payment.getPayCode(), payment.getPayMethod());
    }

    private Payment convertToModel(PaymentDTO paymentDTO) {
        if (paymentDTO == null) {
            return null;
        }
        return new Payment(paymentDTO.getPayCode(), paymentDTO.getPayMethod());
    }
}
