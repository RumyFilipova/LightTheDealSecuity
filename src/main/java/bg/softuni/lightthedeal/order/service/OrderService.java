package bg.softuni.lightthedeal.order.service;

import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.entity.StatusOffer;
import bg.softuni.lightthedeal.offer.repository.OfferRepository;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.order.repository.OrderRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.CreateOrderRequest;
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
    public Order createOrder(CreateOrderRequest request, User user) {

        Offer offer = offerRepository.findByIdAndUser(request.offerId(),user)
                .orElseThrow(()-> new RuntimeException("Offer not found"));

        if(offer.getStatusOffer().equals(StatusOffer.CONFIRMED)){
            throw new RuntimeException("The offer is not confirmed");
        }

        Order order = Order.builder()
                .orderNumber(generateOrderNumber())
                .user(user)
                .customer(offer.getCustomer())
                .premise(offer.getPremise())
                .offer(offer)
                .deadline(request.deadline())
                .createdOn(LocalDateTime.now())
                .build();
        return orderRepository.save(order);
    }

    private String generateOrderNumber() {

        return "OR-%S-%05d".formatted(LocalDate.now().getYear(),orderRepository.count()+1);
    }

}
