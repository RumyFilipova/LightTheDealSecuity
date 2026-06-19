package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.assistance.service.AssistanceService;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.materials.service.MaterialService;
import bg.softuni.lightthedeal.offer.service.OfferService;
import bg.softuni.lightthedeal.order.service.OrderService;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.premise.service.PremiseService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.OrderServiceRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
private final UserService userService;
private final OrderService orderService;
private final CustomerService customerService;
    private final PremiseService premiseService;
    private final AssistanceService assistanceService;
    private final MaterialService materialService;
    private final OfferService offerService;

    public OrderController(UserService userService, OrderService orderService, CustomerService customerService, PremiseService premiseService, AssistanceService assistanceService, MaterialService materialService, OfferService offerService) {
        this.userService = userService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.premiseService = premiseService;
        this.assistanceService = assistanceService;
        this.materialService = materialService;
        this.offerService = offerService;
    }

    @GetMapping
    public ModelAndView getOrder(HttpSession session){

        UUID userId= (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

//        List<Customer> customers = customerService.getAllCustomersForUser(user);
//        UUID premiseId= customers.stream().findAny().ifPresent(customer -> {
//            customer.getPremises().
//        })
        ModelAndView mv = new ModelAndView();

        mv.setViewName("order");
        mv.addObject("orderRequest", new OrderServiceRequest());
        //TODO
//        mv.addObject("customerList", customerService.getAllMaterialLinesResponcesForTheUser(user));
//        mv.addObject("premisesList", premiseService.getAllAssistanceLinesResponcesForTheUser(user));
        mv.addObject("offersList", userService.getAllOffersForUSer(user));
        mv.addObject("userId",userId);

        return mv;
    }

    /*
    public String addOrder(){}
    public String updateOrder(){}
    public String removeOrder(){}*/
}
