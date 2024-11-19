package org.labs.lab4back.contollers;

import jakarta.servlet.http.HttpServletResponse;
import org.labs.lab4back.entities.User;
import org.labs.lab4back.models.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthApiController {
    @Autowired
    private Auth auth;
    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        if(!auth.doAuth(username, password)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        return auth.getUser();
    }
    @PostMapping("/register")
    @ResponseBody
    public User register(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        if(auth.register(username, password)){
            auth.doAuth(username, password);
            return auth.getUser();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return null;
    }
}
