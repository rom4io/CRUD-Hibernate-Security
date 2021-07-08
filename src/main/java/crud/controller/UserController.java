package crud.controller;

import crud.Service.UserService;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users" , userService.allUsers());
        return "users/allUsers";
    }

    @GetMapping("/users/{id}")
    public String userById(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.userByID(id));
        return "users/show";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user){
        return "users/new";
    }


    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.userByID(id));
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete (@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }

}
