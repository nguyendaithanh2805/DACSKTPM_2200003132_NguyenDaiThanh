package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Order;
import org.example.coffeeshopwebsite.model.OrderDetail;
import org.example.coffeeshopwebsite.model.ProductCart;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.OrderDetailRepository;
import org.example.coffeeshopwebsite.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserService userService;
    private final ProductCartService productCartService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, UserService userService, ProductCartService productCartService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userService = userService;
        this.productCartService = productCartService;
    }

    @Override
    public void saveOrder(Order order) {
        User user = userService.getCurrentUser();
        if (order.getId() == null) {
            order.setOrderDate(new Date());
            order.setStatus(false);
            order.setShippingDate(new Date());
            order.setUser(user);
            orderRepository.save(order);
            logger.info("Save order successfully");
            saveOrderDetails(order);
        } else {
            Optional<Order> optionalOrder = orderRepository.findById(order.getId());
            if (optionalOrder.isPresent()) {
                Order existingOrder = optionalOrder.get();
                existingOrder.setOrderDate(new Date());
                existingOrder.setStatus(false);
                existingOrder.setShippingDate(new Date());
                existingOrder.setUser(user);
                orderRepository.save(existingOrder);
                saveOrderDetails(existingOrder);
                logger.info("Update order successfully");
            }
        }
    }

    private void saveOrderDetails(Order order) {
        // Lay tat ca san pham trong gio hang
        List<ProductCart> productCarts = productCartService.getAllProductByUser();
        // Su dung foreach de lap qua tung san pham trong list gio hang do
        for (ProductCart productCart : productCarts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(productCart.getProduct());
            orderDetail.setQuantity(productCart.getQuantity());
            orderDetailRepository.save(orderDetail);
            logger.info("Save order detail successfully");
        }
    }
}
