package com.hp.manner.controller;

import com.hp.manner.model.User;
import com.hp.manner.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class);

    public static final String ADMIN_HOME_PAGE = "admin/home.html";
    public static final String ADMIN_USER_MANAGEMENT_PAGE = "admin/user_manage.html";

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping({ "/", "/index", "/home" })
    public String homePage(ModelMap modelMap) {
        modelMap.addAttribute("message", "This is Admin home Page");
        return ADMIN_HOME_PAGE;
    }

    @ModelAttribute("allUsers")
    public List<User> users() {
        return userService.listAllUsers();
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @RequestMapping("/users")
    public String userManagePage(ModelMap modelMap) {
        modelMap.addAttribute("message", "This is User management page");
        return ADMIN_USER_MANAGEMENT_PAGE;
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ADMIN_USER_MANAGEMENT_PAGE;
        }
        userService.addUser(user);
        return "redirect:/admin/users";
    }

}