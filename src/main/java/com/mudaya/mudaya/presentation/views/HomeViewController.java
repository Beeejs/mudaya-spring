package com.mudaya.mudaya.presentation.views;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/")
    public String home(HttpSession session) {
        Object currentUser = session.getAttribute("currentUser");

        if (currentUser == null) {
            return "redirect:/login";
        }

        return "index"; // si está logueado, va a index.html
    }
}
