/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.itstep.java.spring.mvc;

import org.itstep.java.jdbc.simplemvc.UserService;
import org.itstep.java.lesson1.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String user(
            @RequestParam(value = "id",required = true, defaultValue = "1")
            Integer id,
            ModelMap model) {
        User u = userService.find(id);
        model.addAttribute("user", u);
        return "user";
    }
}
