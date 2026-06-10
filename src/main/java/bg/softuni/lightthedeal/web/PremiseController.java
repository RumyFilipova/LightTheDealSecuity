package bg.softuni.lightthedeal.web;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.premise.entity.PremiseType;
import bg.softuni.lightthedeal.premise.service.PremiseService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.PremiseServiceRequest;
import bg.softuni.lightthedeal.web.DTO.PremiseUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/premise")
public class PremiseController {

    private  final PremiseService premiseService;
    private final UserService userService;
    private final CustomerService customerService;

    @Autowired
    public PremiseController(PremiseService premiseService, UserService userService, CustomerService customerService) {
        this.premiseService = premiseService;
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView getPremise(@RequestParam("userId")UUID userId){

        // TODO: replace with @AuthenticationPrincipal
        User user = userService.getById(userId);

        List<Premise> premises = premiseService.getAllForUser(user);
        List<Customer> customers =customerService.getAllCustomersForUSer(user);

        ModelAndView modelAndView = new ModelAndView();

modelAndView.setViewName("premise");

modelAndView.addObject("premises", premises);
modelAndView.addObject("customers", customers);
modelAndView.addObject("premiseRequest",new PremiseServiceRequest());
modelAndView.addObject("premiseUpdateRequest",new PremiseUpdateRequest());
modelAndView.addObject("premiseTypes", PremiseType.values());
modelAndView.addObject("userId",userId
);

        return modelAndView;
}

   /*
    public String addPremise(){}
    public String updatePremise(){}
    public String removePremise(){}*/

}
