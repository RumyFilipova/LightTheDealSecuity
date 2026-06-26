package bg.softuni.lightthedeal.web.controllers;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.order.entity.StatusOrder;
import bg.softuni.lightthedeal.order.service.OrderService;
import bg.softuni.lightthedeal.premise.service.PremiseService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.OfferOptionResponse;
import bg.softuni.lightthedeal.web.DTO.OrderCreateRequest;
import bg.softuni.lightthedeal.web.DTO.OrderUpdateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final PremiseService premiseService;

    public OrderController(UserService userService, OrderService orderService, CustomerService customerService, PremiseService premiseService) {
        this.userService = userService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.premiseService = premiseService;
    }

    @GetMapping
    public ModelAndView getOrder(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        List<Offer> offers = userService.getAllOffersForUSer(user);
        ModelAndView mv = new ModelAndView();

        mv.setViewName("order");
        mv.addObject("orderRequest", new OrderCreateRequest());
        mv.addObject("orderUpdateRequest", new OrderUpdateRequest());
        mv.addObject("offersList",offers);
        mv.addObject("offerOption",offers.stream().map(OrderController::fromOffer).collect(Collectors.toList()));
        mv.addObject("ordersList", userService.getAllOrdersForUSer(user));
        mv.addObject("customersList", customerService.getAllCustomerServiceResponsesForUser(user));
        mv.addObject("premisesList", premiseService.getAllPremisesResponsesForTheUser(user));
        mv.addObject("statusOrder", StatusOrder.values());
        mv.addObject("userId", userId);

        return mv;
    }

    @PostMapping
    public ModelAndView createOrder(@Valid @ModelAttribute("orderRequest") OrderCreateRequest orderCreateRequest, BindingResult bindingResult, HttpSession session) {
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()) {

            List<Offer>offers=userService.getAllOffersForUSer(user);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("order");

            mv.addObject("orderRequest", orderCreateRequest);
            mv.addObject("orderUpdateRequest", new OrderUpdateRequest());
            mv.addObject("offersList",offers);
            mv.addObject("offerOption",offers.stream().map(OrderController::fromOffer).collect(Collectors.toList()));
            mv.addObject("ordersList", userService.getAllOrdersForUSer(user));
            mv.addObject("statusOrder", StatusOrder.values());
            mv.addObject("user", userId);

            return mv;
        }

        orderService.createOrder(orderCreateRequest, user);
        return new ModelAndView("redirect:/order");
    }

    @PostMapping("/update")
    public ModelAndView updateOrder(HttpSession session, @Valid @ModelAttribute("orderUpdateRequest") OrderUpdateRequest request, BindingResult bindingResult) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()) {
List<Offer> offers =userService.getAllOffersForUSer(user);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("order");

            mv.addObject("orderRequest", new OrderCreateRequest());
            mv.addObject("orderUpdateRequest", request);
            mv.addObject("ordersList", userService.getAllOrdersForUSer(user));
            mv.addObject("offersList",offers);
            mv.addObject("offerOption",offers.stream().map(OrderController::fromOffer).collect(Collectors.toList()));
            mv.addObject("statusOrder", StatusOrder.values());
            mv.addObject("user", user.getId());

            orderService.updateOrder(request, user);
            return mv;
        }
        orderService.updateOrder(request, user);
        return new ModelAndView("redirect:/order");
    }

    @PostMapping("/{id}/reissue")
    public ModelAndView reissueOrder(@PathVariable UUID id, HttpSession session) {
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        orderService.reIssueOrder(id, user);
        return new ModelAndView("redirect:/order");
    }

    public static OfferOptionResponse fromOffer(Offer offer) {
        return OfferOptionResponse.builder()
                .id(offer.getId())
                .offerName(offer.getOfferName())
                .customerFirstName(offer.getCustomer().getFirstName())
                .customerLastName(offer.getCustomer().getLastName())
                .premiseName(offer.getPremise().getName())
                .premiseAddress(offer.getPremise().getAddress())
                .build();
    }
}
