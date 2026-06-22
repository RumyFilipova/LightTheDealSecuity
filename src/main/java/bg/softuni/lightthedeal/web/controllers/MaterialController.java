package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.materials.entities.Category;
import bg.softuni.lightthedeal.materials.entities.Unit;
import bg.softuni.lightthedeal.materials.service.MaterialService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.repository.UserRepository;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.MaterialServiceRequest;
import bg.softuni.lightthedeal.web.DTO.MaterialUpdateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;
    private final UserService userService;


    @Autowired
    public MaterialController(MaterialService materialService, UserService userService, UserProperties userProperties) {
        this.materialService = materialService;
        this.userService = userService;

    }

    @GetMapping
    public ModelAndView getMaterial(HttpSession session) {

        UUID userId =(UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("material");

        modelAndView.addObject("materialRequest", new MaterialServiceRequest());
        modelAndView.addObject("materialUpdateRequest", new MaterialUpdateRequest());
        modelAndView.addObject("materialsList", materialService.getAllMaterialServiceResponsesForUser(user));
        modelAndView.addObject("category", Category.values());
        modelAndView.addObject("unit", Unit.values());
        modelAndView.addObject("user", user.getId());


        return modelAndView;
    }
    @PostMapping
    public ModelAndView addMaterial(@Valid @ModelAttribute("materialRequest") MaterialServiceRequest materialRequest, BindingResult bindingResult, HttpSession session) {

        UUID userId =(UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if(bindingResult.hasErrors()){
            return new ModelAndView("material");
        }
        materialService.createMaterial(materialRequest, user);
        return new ModelAndView("redirect:/material");
    }

    @PostMapping("/{id}/delete")
    public ModelAndView removeMaterial(@PathVariable UUID id, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        materialService.deleteMaterial(id,user);
        return new ModelAndView("redirect:/material");
    }


    @PostMapping("/{id}/update")
    public ModelAndView updateMaterial(@PathVariable UUID id,
                                       @Valid @ModelAttribute("materialUpdateRequest") MaterialUpdateRequest request,
                                       BindingResult bindingResult,
                                       HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()){
            return new ModelAndView("material");
        }

        materialService.updateMaterial(request,id,user);

        return new ModelAndView("redirect:/material");
    }
}
