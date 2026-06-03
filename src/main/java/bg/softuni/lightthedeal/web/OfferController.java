package bg.softuni.lightthedeal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offer")
public class OfferController {

    @GetMapping
    public String getOffer(){

        return "offer";
    }

   /*
    public String addOffer(){}
    public String updateOffer(){}
    public String removeOffer(){}*/
}
