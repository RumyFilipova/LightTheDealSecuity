package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.premise.entity.PremiseType;
import bg.softuni.lightthedeal.premise.service.PremiseService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.PremiseServiceRequest;
import bg.softuni.lightthedeal.web.DTO.PremiseUpdateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @GetMapping()
    public ModelAndView getPremise(HttpSession session) {

        UUID userId =(UUID) session.getAttribute("userId");

        User user = userService.getById(userId);

        List<Customer> customers = customerService.getAllCustomersForUser(user);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("premise");

        modelAndView.addObject("customers", customers);
        modelAndView.addObject("premiseRequest", new PremiseServiceRequest());
        modelAndView.addObject("premiseUpdateRequest", new PremiseUpdateRequest());
        modelAndView.addObject("premiseList", premiseService.getAllPremisesResponsesForTheUser(user));
        modelAndView.addObject("premiseTypes", PremiseType.values());
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("userId", user.getId());

        return modelAndView;
    }


    @PostMapping
    public ModelAndView addPremise(@Valid @ModelAttribute("premiseRequest") PremiseServiceRequest premiseServiceRequest,
                             BindingResult bindingResult,
                             HttpSession session){
if(bindingResult.hasErrors()){
    return new  ModelAndView("premise");
}
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);
        premiseService.cretatePremise(premiseServiceRequest,user);

        return new   ModelAndView("redirect:/premise");
    }

@PostMapping ("/{id}/delete")
public String removeClient(@PathVariable UUID id,HttpSession session){

        UUID userId = (UUID) session.getAttribute("userId");
    //replace with session
    User user = userService.getById(userId);

    premiseService.deletePremise(id,user);
    return "redirect:/premise";
}

@PostMapping("/{id}/update")
    public ModelAndView updatePremise(@PathVariable UUID id,
                                @Valid @ModelAttribute("premiseUpdateRequest") PremiseUpdateRequest premiseUpdateRequest,
                                BindingResult  bindingResult,
                                HttpSession session){

UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if(bindingResult.hasErrors()){
            new ModelAndView("premise");
        }
        premiseService.updatePremise(premiseUpdateRequest,id,user);

        return  new ModelAndView("redirect:/premise");
}

}
