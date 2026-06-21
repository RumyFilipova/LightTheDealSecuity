package bg.softuni.lightthedeal.order.service;

import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.entity.StatusOffer;
import bg.softuni.lightthedeal.offer.repository.OfferRepository;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.order.entity.StatusOrder;
import bg.softuni.lightthedeal.order.repository.OrderRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.OrderCreateRequest;
import bg.softuni.lightthedeal.web.DTO.OrderCreateResponse;
import bg.softuni.lightthedeal.web.DTO.OrderUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

        Order order = orderRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        order.setDeadline(request.getDeadline());
        order.setDescription(request.getNote());
        order.setStausOrder(request.getStatus());

        return orderRepository.save(order);
    }

    public Order reIssueOrder(UUID orderId, User user) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order not found"));
        order.setOrderNumber(updateOrderNumber(orderId));

        return orderRepository.save(order);
    }

    private String updateOrderNumber(UUID orderId) {

        int count = 1;

        return "%S-REV-%05d".formatted(orderId, count + 1);
    }

    public List<OrderCreateResponse> getAllOrdersCreateResponse(User user) {
        return orderRepository.findAllByUser(user)
                .stream()
                .map(this::response)
                .toList();
    }

    private OrderCreateResponse response(Order order) {

        return OrderCreateResponse.builder()
                .offerId(order.getId())
                .orderNumber(order.getOrderNumber())
                .deadline(order.getDeadline())
                .stausOrder(order.getStausOrder())
                .description(order.getDescription())
                .build();
    }
}
