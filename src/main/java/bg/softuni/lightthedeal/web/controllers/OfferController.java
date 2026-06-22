package bg.softuni.lightthedeal.web.controllers;
import bg.softuni.lightthedeal.assistance.service.AssistanceService;
import bg.softuni.lightthedeal.assistance.service.OfferAssistanceLineService;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.materials.service.MaterialService;
import bg.softuni.lightthedeal.materials.service.OfferMaterialLineService;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.entity.StatusOffer;
import bg.softuni.lightthedeal.offer.service.OfferService;
import bg.softuni.lightthedeal.premise.service.PremiseService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.AssistanceLineUpdateRequest;
import bg.softuni.lightthedeal.web.DTO.MaterialLineUpdateRequest;
import bg.softuni.lightthedeal.web.DTO.OfferServiceRequest;
import bg.softuni.lightthedeal.web.DTO.OfferUpdateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final UserService userService;
    private final MaterialService materialService;
    private final AssistanceService assistanceService;
    private final PremiseService premiseService;
    private final CustomerService customerService;
private final OfferMaterialLineService  offerMaterialLineService;
private final OfferAssistanceLineService offerAssistanceLineService;

    @Autowired
    public OfferController(OfferService offerService, UserService userService, MaterialService materialService, AssistanceService assistanceService, PremiseService premiseService, CustomerService customerService, OfferMaterialLineService offerMaterialLineService, OfferAssistanceLineService offerAssistanceLineService) {
        this.offerService = offerService;
        this.userService = userService;
        this.materialService = materialService;
        this.assistanceService = assistanceService;
        this.premiseService = premiseService;
        this.customerService = customerService;
        this.offerMaterialLineService = offerMaterialLineService;
        this.offerAssistanceLineService = offerAssistanceLineService;
    }

    @GetMapping
    public ModelAndView getOffer(HttpSession session){

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);


        ModelAndView mv = new ModelAndView();
        mv.setViewName("offer");

        mv.addObject("offerRequest", new OfferServiceRequest());
        mv.addObject("materialsList", materialService.getAllMaterialServiceResponsesForUser(user));
        mv.addObject("assistantsList", assistanceService.getAllAssistanceResponsesForUser(user));
        mv.addObject("customersList", customerService.getAllCustomerServiceResponsesForUser(user));
        mv.addObject("premisesList", premiseService.getAllPremisesResponsesForTheUser(user));
        mv.addObject("offersList", userService.getAllOffersForUSer(user));
        mv.addObject("nextOfferNumber", offerService.generateOfferNumber());
        mv.addObject("offerUpdateRequest", new OfferUpdateRequest());
        mv.addObject("statusOffer", StatusOffer.values());
        mv.addObject("user", user.getId());

        return mv;
    }

    @PostMapping
    public ModelAndView addOffer(@Valid @ModelAttribute("offerRequest") OfferServiceRequest request,BindingResult bindingResult, HttpSession session){
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if(bindingResult.hasErrors()){

            ModelAndView mv = new ModelAndView();
            mv.setViewName("offer");

            mv.addObject("offerRequest", request);
            mv.addObject("materialsList", materialService.getAllMaterialServiceResponsesForUser(user));
            mv.addObject("assistantsList", assistanceService.getAllAssistanceResponsesForUser(user));
            mv.addObject("customersList", customerService.getAllCustomerServiceResponsesForUser(user));
            mv.addObject("premisesList", premiseService.getAllPremisesResponsesForTheUser(user));
            mv.addObject("offersList", userService.getAllOffersForUSer(user));
            mv.addObject("offerUpdateRequest", new OfferUpdateRequest());
            mv.addObject("statusOffer", StatusOffer.values());
            mv.addObject("user", user.getId());

            return mv;
        }

        offerService.createOffer(request,user);
        return new ModelAndView("redirect:/offer");
    }

    @PostMapping("/update")
    public ModelAndView updateOffer(@Valid @ModelAttribute("offerUpdateRequest") OfferUpdateRequest request, BindingResult bindingResult, HttpSession session){
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("offer");
            mv.addObject("offerRequest", new OfferServiceRequest());
            mv.addObject("materialsList", materialService.getAllMaterialServiceResponsesForUser(user));
            mv.addObject("assistantsList", assistanceService.getAllAssistanceResponsesForUser(user));
            mv.addObject("customersList", customerService.getAllCustomerServiceResponsesForUser(user));
            mv.addObject("premisesList", premiseService.getAllPremisesResponsesForTheUser(user));
            mv.addObject("offersList", userService.getAllOffersForUSer(user));
            mv.addObject("statusOffer", StatusOffer.values());
            mv.addObject("offerUpdateRequest", request);
            mv.addObject("user", userId);
            return mv;
        }

        offerService.updateOffer(request, user);
        return new ModelAndView("redirect:/offer");
    }


    @PostMapping("/{id}/delete")
    public ModelAndView deleteOffer(@PathVariable UUID id,HttpSession session){
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        offerService.deleteOffer(id,user);
        return new ModelAndView("redirect:/offer");
    }

    @PostMapping("/{id}/material-line/update")
    public ModelAndView updateMaterialLine(@PathVariable UUID id, @Valid @ModelAttribute MaterialLineUpdateRequest request,
                                           BindingResult bindingResult, HttpSession session) {
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);
        Offer offer = offerService.getByIdAndUser(id, user);

        offerMaterialLineService.updateOfferMaterialLine(request, offer, user);
        offerService.recalculateTotalAmount(id, user);

        return new ModelAndView("redirect:/offer");
    }

    @PostMapping("/{id}/assistance-line/update")
    public ModelAndView updateAssistanceLine(@PathVariable UUID id, @Valid @ModelAttribute AssistanceLineUpdateRequest request,
                                           BindingResult bindingResult, HttpSession session) {
        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);
        Offer offer = offerService.getByIdAndUser(id, user);

        offerAssistanceLineService.updateOfferAssistanceLine(request, offer, user);
        offerService.recalculateTotalAmount(id, user);

        return new ModelAndView("redirect:/offer");
    }

}
