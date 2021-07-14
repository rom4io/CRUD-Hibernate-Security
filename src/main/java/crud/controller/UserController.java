package crud.controller;

import crud.Service.UserService;
import crud.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users" , userService.allUsers());
        return "users/allUsers";
    }


    @RequestMapping(value = "/users/{id}")
    public String userById(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.userByID(id));
        return "users/show";
    }

    @GetMapping(value = "/users/new")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "users/new";
    }


    @PostMapping(value = "/users/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/users/new";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.userByID(id));
        return "users/edit";
    }

    @PatchMapping(value = "/users/{id}")
    public String update(@ModelAttribute("user")@Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()){
            return "/users/edit";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/users/{id}")
    public String delete (@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }

}
