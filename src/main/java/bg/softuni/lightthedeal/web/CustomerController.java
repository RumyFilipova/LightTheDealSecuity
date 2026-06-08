package bg.softuni.lightthedeal.web;

import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.CustomerServiceRequest;
import bg.softuni.lightthedeal.web.DTO.CustomerUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final UserService userService;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView getCustomerPage() {

        // TODO: replace hardcoded UUID with logged-in user from session/security
        User user =userService.getById(UUID.fromString("my-user-id"));
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("customer");
        modelAndView.addObject("customerRequest", new CustomerServiceRequest());
        modelAndView.addObject("customerUpdateRequest", new CustomerUpdateRequest());
        modelAndView.addObject("customersList", customerService.getAllCustomersForUSer(user));
        modelAndView.addObject("user",user.getId());
        return modelAndView;
    }

   /*
    public String addCustomer(){}
    public String updateCustomer(){}
    public String removeCustomer(){}*/
}
