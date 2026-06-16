package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.premise.entity.PremiseType;
import bg.softuni.lightthedeal.premise.service.PremiseService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.repository.UserRepository;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.PremiseServiceRequest;
import bg.softuni.lightthedeal.web.DTO.PremiseUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/premise")
public class PremiseController {

    private final PremiseService premiseService;
    private final UserService userService;
    private final CustomerService customerService;
    private final UserProperties userProperties;

    @Autowired
    public PremiseController(PremiseService premiseService, UserService userService, CustomerService customerService, UserProperties userProperties) {
        this.premiseService = premiseService;
        this.userService = userService;
        this.customerService = customerService;
        this.userProperties = userProperties;
    }

    @GetMapping("/{id}")
    public ModelAndView getPremise(@PathVariable UUID id) {

        // TODO: replace with @AuthenticationPrincipal
        User user = userService.getById(id);

        List<Premise> premises = premiseService.getAllForUser(user);
        List<Customer> customers = customerService.getAllCustomersForUser(user);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("premise");

        modelAndView.addObject("premises", premises);
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("premiseRequest", new PremiseServiceRequest());
        modelAndView.addObject("premiseUpdateRequest", new PremiseUpdateRequest());
        modelAndView.addObject("premiseTypes", PremiseType.values());
        modelAndView.addObject("userId", user.getId());

        return modelAndView;
    }

//    @GetMapping("/{id}/edit")
//    public ModelAndView updatePremise(@PathVariable UUID id) {
//
//        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());
//        Premise premise = premiseService.getByIdAndUser(id, user);
//
//        List<Premise> premises = premiseService.getAllForUser(user);
//        List<Customer> customers = customerService.getAllCustomersForUser(user);
//
//        PremiseUpdateRequest
//
//    }

    @PostMapping
    public String addPremise(@ModelAttribute("premiseRequest") PremiseServiceRequest premiseServiceRequest){

        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());
        premiseService.cretatePremise(premiseServiceRequest,user);

        return "redirect:/premise";
    }



   /*
    public String addPremise(){}
    public String updatePremise(){}
    public String removePremise(){}*/

}
