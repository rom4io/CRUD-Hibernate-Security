package crud.controller;

import crud.Service.AdminService;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AdminService service;


    @GetMapping ("/userinfo")
    public String currentUser(Model model, Principal principal){
        User user = service.userByEmail(principal.getName());
        model.addAttribute("currentUser", user);
        return "user/showCurrentUser";
    }

}
