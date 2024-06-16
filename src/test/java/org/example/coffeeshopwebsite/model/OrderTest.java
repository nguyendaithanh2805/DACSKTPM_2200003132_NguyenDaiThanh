package org.example.coffeeshopwebsite.model;

import jakarta.transaction.Transactional;
import org.example.coffeeshopwebsite.repository.OrderRepository;
import org.example.coffeeshopwebsite.repository.PaymentRepository;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.example.coffeeshopwebsite.repository.ShippingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@Transactional
//@Rollback(value = true)
class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    private Order order;

    @BeforeEach
    void setUp() {
        Shipping shipping = new Shipping();
        shipping.setShippingFee(35.0);
        shipping = shippingRepository.save(shipping);

        // Tạo products
        Product product1 = new Product();
        product1.setName("Coffee");
        product1.setDescription("Roasted coffee beans");
        product1.setDiscount(9.0);
        product1.setImage("espresso.jpg");
        product1.setQuantity(2);
        product1.setSellingPrice(10.0);
        product1 = productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Espresso");
        product2.setDescription("Strong coffee");
        product2.setDiscount(0.0);
        product2.setImage("espresso.jpg");
        product2.setQuantity(100);
        product2.setSellingPrice(5.0);
        product2 = productRepository.save(product2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Payment payment1 = new Payment();
        payment1.setPaymentMethod("Cash");
        payment1.setOrder(order);
        payment1 = paymentRepository.save(payment1);

        Payment payment2 = new Payment();
        payment2.setPaymentMethod("Mastercard");
        payment2.setOrder(order);
        payment2 = paymentRepository.save(payment2);

        List<Payment> payments = new ArrayList<>();
        payments.add(payment1);
        payments.add(payment2);

        Date orderDate = new Date();
        Date shippingDate = new Date(orderDate.getTime() + (3 * 24 * 60 * 60 * 1000));
        order = new Order();
        order.setOrderDate(orderDate);
        order.setShippingDate(shippingDate);
        order.setStatus(true);
        order.setNote("Test order");
        for (int i = 0; i < payments.size(); i++)
        {
            order.setPayment(payments.get(i));
        }
        order.setProducts(products);
        order.setShipping(shipping);
        orderRepository.save(order);
    }

//    @Test
//    public void saveOrder() {
//        assertNotNull(order.getId());
//        Order saveOrder = orderRepository.findById(order.getId()).orElse(null);
//        assertNotNull(saveOrder);
//        assertEquals(order.getOrderDate(), saveOrder.getOrderDate());
//        assertEquals(order.getShippingDate(), saveOrder.getShippingDate());
//        assertEquals(order.getStatus(), saveOrder.getStatus());
//        assertEquals(order.getNote(), saveOrder.getNote());
//        assertNotNull(saveOrder.getPayment());
//        assertEquals(order.getPayment().size(), saveOrder.getPayment().size());
//        for (int i = 0; i < order.getPayment(); i++) {
//            assertEquals(order.getPayment().get(i).getPaymentMethod(), saveOrder.getPayment().get(i).getPaymentMethod());
//        }
//        assertEquals(order.getProducts().size(), saveOrder.getProducts().size());
//        assertEquals(order.getShipping().getId(), saveOrder.getShipping().getId());
//    }
}