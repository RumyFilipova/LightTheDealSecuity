package bg.softuni.lightthedeal.web.controllers;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.ChangePasswordRequest;
import bg.softuni.lightthedeal.web.DTO.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}/profile")
    public ModelAndView getUserProfilePage(@PathVariable UUID id) {

        User user = userService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @GetMapping("/{id}/edit-profile")
    public ModelAndView getEditUserDetailsPage(@PathVariable UUID id) {

        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("edit-profile");
        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }


    @PostMapping("/{id}/edit-profile")
    public ModelAndView saveEditProfile(@PathVariable UUID id,
                                  @Valid @ModelAttribute("userUpdateDto") UserUpdateRequest dto,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit-profile");
            modelAndView.addObject("currentUser", userService.getById(id));
            return modelAndView;
        }
        userService.updateUser(dto, id);
        return new ModelAndView("redirect:/profile/" + id + "/edit-profile");
    }

    @PostMapping("/{id}/edit-profile/contact")
    public ModelAndView saveContactDetails(@PathVariable UUID id,
                                     @Valid @ModelAttribute UserUpdateRequest dto,BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit-profile");
            modelAndView.addObject("currentUser", userService.getById(id));
            return modelAndView;
        }

        userService.updateUser(dto, id);
        return new ModelAndView("redirect:/profile/" + id + "/edit-profile");
    }

    @PostMapping("/{id}/edit-profile/avatar/remove")
    public ModelAndView removeAvatar(@PathVariable UUID id) {
        userService.removeProfilePicture(id);
        return new ModelAndView("redirect:/profile/" + id + "/edit-profile");
    }

    @GetMapping("/{id}/change-password")
    public ModelAndView getEditPasswordPage(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("change-password");
        modelAndView.addObject("passwordForm", new ChangePasswordRequest());
        modelAndView.addObject("userId", id);

        return modelAndView;
    }

    @PostMapping("/{id}/change-password")
    public ModelAndView editPasswordDetails(@PathVariable UUID id,
                                      @Valid @ModelAttribute("passwordForm") ChangePasswordRequest dto, BindingResult bindingResult,Model model) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("change-password");
            modelAndView.addObject("userId", id);
            return modelAndView;
        }

        try {
            userService.updatePassword(dto, id);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("passwordForm", dto);
            model.addAttribute("userId", id);
            return new ModelAndView("change-password");
        }

        return new ModelAndView("redirect:/login");
    }


}
