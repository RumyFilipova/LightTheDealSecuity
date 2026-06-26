package bg.softuni.lightthedeal.web.controllers;
import bg.softuni.lightthedeal.customer.entity.CustomerType;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.CustomerServiceRequest;
import bg.softuni.lightthedeal.web.DTO.CustomerUpdateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final UserService userService;
    private final CustomerService customerService;
    private final UserProperties userProperties;

    @Autowired
    public CustomerController(UserService userService, CustomerService customerService, UserProperties userProperties) {
        this.userService = userService;
        this.customerService = customerService;
        this.userProperties = userProperties;
    }

    @GetMapping
    public ModelAndView getCustomerPage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        // TODO: replace hardcoded UUID with logged-in user from session/security
        User user = userService.getById(userId);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("customer");
        modelAndView.addObject("customerRequest", new CustomerServiceRequest());
        modelAndView.addObject("customerUpdateRequest", new CustomerUpdateRequest());
        modelAndView.addObject("customersList", customerService.getAllCustomerServiceResponsesForUser(user));
        modelAndView.addObject("customerTypes", CustomerType.values());
        modelAndView.addObject("user", user.getId());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addCustomer(@Valid @ModelAttribute("customerRequest") CustomerServiceRequest customerServiceRequest, BindingResult bindingResult, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if(bindingResult.hasErrors()){
            return new ModelAndView("customer");
        }

        customerService.createCustomer(customerServiceRequest, user);
        return new ModelAndView("redirect:/customer");
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteCustomer(@PathVariable UUID id,  HttpSession session) {
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);
        customerService.deleteById(id, user);

        return new ModelAndView("redirect:/customer");
    }

    @PostMapping("/{id}/update")
    public ModelAndView updateCustomer(@PathVariable UUID id,@Valid @ModelAttribute("customerUpdateRequest")  CustomerUpdateRequest request,BindingResult result ,HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if(result.hasErrors()){
            return new ModelAndView("customer");
        }
        customerService.updateCustomer(request,id,user);

        return new ModelAndView("redirect:/customer");
    }

}
