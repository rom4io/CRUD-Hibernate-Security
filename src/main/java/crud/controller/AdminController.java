package crud.controller;

import crud.Service.AdminService;
import crud.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @RequestMapping(value = "/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users" , adminService.allUsers());
        return "admin/allUsers";
    }


    @RequestMapping(value = "/users/{id}")
    public String userById(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", adminService.userByID(id));
        return "admin/show";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        //user.setRoleSet(2L);

        return "admin/new";
    }


    @PostMapping(value = "/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/admin/new";
        }
        adminService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", adminService.userByID(id));
        return "admin/edit";
    }

    @PatchMapping(value = "/users/{id}")
    public String update(@ModelAttribute("user")@Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()){
            return "/admin/edit";
        }
        adminService.update(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping(value = "/users/{id}")
    public String delete (@PathVariable("id") Long id){
        adminService.delete(id);
        return "redirect:/admin/users";
    }


}
