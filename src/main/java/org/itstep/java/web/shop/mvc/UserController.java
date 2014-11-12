/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.itstep.java.web.shop.mvc;

import java.security.Principal;
import org.itstep.java.web.shop.service.UserService;
import org.itstep.java.web.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/user")
@SessionAttributes("current")
public class UserController {
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String user(
            UsernamePasswordAuthenticationToken currentUser,
            @RequestParam(value = "id",required = true, defaultValue = "1")
            Integer id,
            Model model) {
        User u = userService.find(id);
        model.addAttribute("user", u);
        model.addAttribute("current", (User) currentUser.getPrincipal());
        return "user";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        
        return "login";
    }
}
