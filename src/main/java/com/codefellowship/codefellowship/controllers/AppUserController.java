package com.codefellowship.codefellowship.controllers;

import com.codefellowship.codefellowship.models.AppUser;
import com.codefellowship.codefellowship.repos.AppUserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class AppUserController {
    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public String getHomePage(Model m, Principal p) {

        if(p != null) {

            String username = p.getName();
            AppUser user = appUserRepo.findByUsername(username);

            m.addAttribute("username", username);
        }
        return "index.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        AppUser newUser = new AppUser();
        newUser.setUsername(username);
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setDateOfBirth(dateOfBirth);
        newUser.setBio(bio);

        appUserRepo.save(newUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServletRequest(String username, String password) {
        try{
            request.login(username, password);
        } catch(ServletException e) {
            System.out.println("Error While Logging In");
            e.printStackTrace();
        }
    }


}