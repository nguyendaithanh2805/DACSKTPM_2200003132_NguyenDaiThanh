package org.example.coffeeshopwebsite.model;

import org.example.coffeeshopwebsite.repository.OrderRepository;
import org.example.coffeeshopwebsite.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class PaymentTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        Date orderDate = new Date();
        Date shippingDate = new Date(orderDate.getTime() + (3 * 24 * 60 * 60 * 1000));
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setShippingDate(shippingDate);
        order.setStatus(true);
        order.setNote("Test order");
        orderRepository.save(order);

        Order order2 = new Order();
        order2.setOrderDate(orderDate);
        order2.setShippingDate(shippingDate);
        order2.setStatus(true);
        order2.setNote("Test order");
        orderRepository.save(order2);

        Payment payment1 = new Payment(1L,"Cash", order);
        paymentRepository.save(payment1);

        Payment payment2 = new Payment(2L,"MasterCard", order2);
        paymentRepository.save(payment2);
    }

    @Test
    public void savePaymentTest() {
        List<Payment> payments = paymentRepository.findAll();
        assertNotNull(payments);
        assertEquals(2, payments.size());

        Payment savedPayment1 = payments.get(0);
        assertEquals("Cash", savedPayment1.getPaymentMethod());
        assertNotNull(savedPayment1.getOrder());

        Payment savedPayment2 = payments.get(1);
        assertEquals("MasterCard", savedPayment2.getPaymentMethod());
        assertNotNull(savedPayment2.getOrder());
    }
}