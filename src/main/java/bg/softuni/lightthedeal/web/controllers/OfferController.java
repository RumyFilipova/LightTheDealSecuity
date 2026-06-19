package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.assistance.service.AssistanceService;
import bg.softuni.lightthedeal.assistance.service.OfferAssistanceService;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.materials.service.MaterialService;
import bg.softuni.lightthedeal.materials.service.OfferMaterialService;
import bg.softuni.lightthedeal.offer.service.OfferService;
import bg.softuni.lightthedeal.premise.service.PremiseService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.OfferAssistanceLine;
import bg.softuni.lightthedeal.web.DTO.OfferMaterialLine;
import bg.softuni.lightthedeal.web.DTO.OfferServiceRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final UserService userService;
    private final MaterialService materialService;
    private final AssistanceService assistanceService;
    private final OfferAssistanceService offerAssistanceService;
    private final OfferMaterialService offerMaterialService;
    private final PremiseService premiseService;
    private final CustomerService customerService;
    private final UserProperties userProperties;

    @Autowired
    public OfferController(OfferService offerService, UserService userService, MaterialService materialService, AssistanceService assistanceService, OfferAssistanceService offerAssistanceService, UserProperties userProperties, OfferMaterialService offerMaterialService, PremiseService premiseService, CustomerService customerService) {
        this.offerService = offerService;
        this.userService = userService;
        this.materialService = materialService;
        this.assistanceService = assistanceService;
        this.offerAssistanceService = offerAssistanceService;
        this.userProperties = userProperties;
        this.offerMaterialService = offerMaterialService;
        this.premiseService = premiseService;
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView getOffer(HttpSession session){

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        // new empty line to start with
        OfferServiceRequest offerServiceRequest = new OfferServiceRequest();


        offerServiceRequest.setAssistants(new ArrayList<>(java.util.List.of(new OfferAssistanceLine())));
        offerServiceRequest.setMaterials(new ArrayList<>(java.util.List.of(new OfferMaterialLine())));

        ModelAndView mv = new ModelAndView();

        mv.setViewName("offer");
        mv.addObject("offerRequest", offerServiceRequest);
        mv.addObject("materialsList", materialService.getAllMaterialForUser(user));
        mv.addObject("assistance", assistanceService.getAllAssistanceForUSer(user));

        mv.addObject("user", user.getId());

        return mv;
    }

//    @PostMapping("/{offerId}/material/{lineId}/update")
//    public String updateOfferMaterialLine(@PathVariable UUID offerId,
//                                          @PathVariable UUID lineId,
//                                          @ModelAttribute OfferMaterialLine request){
//       offerMaterialService.updateMaterialLine(lineId,request);
//
//       return ("redirect:/offer" + offerId);
//    }
   /*
    public String addOffer(){}
    public String updateOffer(){}
    public String removeOffer(){}*/
}
