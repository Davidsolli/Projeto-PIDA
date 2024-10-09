package com.david.amazonas.dtos.orders;

import com.david.amazonas.domains.payments.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class PaymentDTO {

    private Long paymentId;
    private Instant moment;

    public PaymentDTO(Payment payment) {
        paymentId = payment.getPaymentId();
        moment = payment.getMoment();
    }
}
