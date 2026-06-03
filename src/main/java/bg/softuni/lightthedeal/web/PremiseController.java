package bg.softuni.lightthedeal.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/premise")
public class PremiseController {

@GetMapping
    public String getPremise(){
    return "premise";
}

   /*
    public String addPremise(){}
    public String updatePremise(){}
    public String removePremise(){}*/

}
