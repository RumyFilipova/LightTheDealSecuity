package bg.softuni.lightthedeal.web.controllers;
import bg.softuni.lightthedeal.order.service.OrderService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.OrderCreateRequest;
import bg.softuni.lightthedeal.web.DTO.OrderUpdateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView getOrder(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("order");
        mv.addObject("orderRequest", new OrderCreateRequest());
        mv.addObject("orderUpdateRequest", new OrderUpdateRequest());
        mv.addObject("offersList", userService.getAllOffersForUSer(user));
        mv.addObject("ordersList", orderService.getAllOrdersCreateResponse(user));
        mv.addObject("userId", userId);

        return mv;
    }

    @PostMapping
    public ModelAndView createOrder(@Valid @ModelAttribute("orderRequest") OrderCreateRequest orderCreateRequest, BindingResult bindingResult, HttpSession session) {
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()) {

            ModelAndView mv = new ModelAndView();
            mv.setViewName("order");

            mv.addObject("orderRequest", orderCreateRequest);
            mv.addObject("orderUpdateRequest", new OrderUpdateRequest());
            mv.addObject("offersList", userService.getAllOffersForUSer(user));
            mv.addObject("ordersList", orderService.getAllOrdersCreateResponse(user));
            mv.addObject("user", userId);

            return mv;
        }

        orderService.createOrder(orderCreateRequest, user);
        return new ModelAndView("redirect:/order");
    }

    @PostMapping("/{id}/update")
    public ModelAndView updateOrder(@PathVariable UUID id, HttpSession session, @Valid @ModelAttribute("orderUpdateRequest") OrderUpdateRequest request, BindingResult bindingResult) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()) {

            ModelAndView mv = new ModelAndView();
            mv.setViewName("order");

            mv.addObject("orderRequest", new OrderCreateRequest());
            mv.addObject("orderUpdateRequest", request);
            mv.addObject("offersList", userService.getAllOffersForUSer(user));
            mv.addObject("ordersList", orderService.getAllOrdersCreateResponse(user));
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
}
