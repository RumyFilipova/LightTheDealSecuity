package bg.softuni.lightthedeal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping
    public String getCustomer(){
        return "customer";
    }
   /*
    public String addCustomer(){}
    public String updateCustomer(){}
    public String removeCustomer(){}*/
}
