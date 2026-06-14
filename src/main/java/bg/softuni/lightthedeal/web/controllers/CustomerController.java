package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.customer.entity.CustomerType;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.CustomerServiceRequest;
import bg.softuni.lightthedeal.web.DTO.CustomerUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView getCustomerPage() {

        // TODO: replace hardcoded UUID with logged-in user from session/security
        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("customer");
        modelAndView.addObject("customerRequest", new CustomerServiceRequest());
        modelAndView.addObject("customerUpdateRequest", new CustomerUpdateRequest());
        modelAndView.addObject("customersList", customerService.getAllCustomersForUser(user));
        modelAndView.addObject("customerTypes", CustomerType.values());
        modelAndView.addObject("user", user.getId());
        return modelAndView;
    }

    @PostMapping
    public String addCustomer(@ModelAttribute("customerRequest") CustomerServiceRequest customerServiceRequest) {

        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());

        customerService.createCustomer(customerServiceRequest, user);
        return "redirect:/customer";
    }
   /*
    public String addCustomer(){}
    public String updateCustomer(){}
    public String removeCustomer(){}*/
}
