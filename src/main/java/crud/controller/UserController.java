package crud.controller;

import crud.Service.UserService;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(value = "/users")
//    public String showAllUsers(Model model) {
//        model.addAttribute("users" , userService.allUsers());
//        return "userv/users";
//    }
//
//    @GetMapping(value = "/createUser")
//    public String addUserForm(@ModelAttribute("user") User user) {
//        return "userv/createUser";
//    }
//
//    @PostMapping(value = "/createUser")
//    public String createUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/users";
//    }
//
//
//    @GetMapping(value = "/deleteUser/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/users";
//    }
//
//    @GetMapping(value = "/getUserById/{id}")
//    public String getUserById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("userById" , userService.userByID(id));
//        return "userv/getUserById";
//    }
//
//    @GetMapping(value = "/editUser/{id}")
//    public String updateUserForm(@PathVariable("id") Long id, Model model) {
//        User user = userService.userByID(id);
//        model.addAttribute("updateUser" , user);
//        return "userv/editUser";
//    }
//
//    @PostMapping(value = "/editUser")
//    public String updateUser(@ModelAttribute("user") User user) {
//        userService.update(user);
//        return "redirect:/users";
//    }

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
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.userByID(id));
        return "users/edit";
    }

    @PatchMapping(value = "/users/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/users/{id}")
    public String delete (@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }

}
