package bg.softuni.lightthedeal.order.service;

import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.entity.StatusOffer;
import bg.softuni.lightthedeal.offer.repository.OfferRepository;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.order.entity.StatusOrder;
import bg.softuni.lightthedeal.order.repository.OrderRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.OrderCreateRequest;
import bg.softuni.lightthedeal.web.DTO.OrderUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;

    public OrderService(OrderRepository orderRepository, OfferRepository offerRepository) {
        this.orderRepository = orderRepository;
        this.offerRepository = offerRepository;
    }

    //createOrder form offer
    public Order createOrder(OrderCreateRequest request, User user) {

        Offer offer = offerRepository.findById(request.getOfferId())
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        if (offer.getStatusOffer().equals(StatusOffer.CONFIRMED)) {
            throw new RuntimeException("The offer is not confirmed");
        } else {
            Order order = Order.builder()
                    .orderNumber(generateOrderNumber())
                    .stausOrder(StatusOrder.IN_PROGRESS)
                    .totalAmount(offer.getTotalAmount())
                    .createdOn(LocalDateTime.now())
                    .completedOn(request.getCompletedOn())
                    .deadline(request.getDeadline())
                    .offer(offer)
                    .user(user)
                    .customer(offer.getCustomer())
                    .premise(offer.getPremise())
                    .description(request.getDescription())
                    .build();

            return orderRepository.save(order);
        }

    }

    private String generateOrderNumber() {

        return "OR-%S-%05d".formatted(LocalDate.now().getYear(), orderRepository.count() + 1);
    }

    public Order updateOrder(OrderUpdateRequest request, User user) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        order.setDeadline(request.getDeadline());
        order.setDescription(request.getDescription());
        order.setStausOrder(request.getStausOrder());

        return orderRepository.save(order);
    }

    public Order reIssueOrder(Order order, User user) {
        order.setOrderNumber(updateOrderNumber(order));

        return orderRepository.save(order);
    }

    private String updateOrderNumber(Order order) {

        int count =1;

        return "%S-REV-%05d".formatted(order.getOrderNumber(), count + 1);
    }
}
