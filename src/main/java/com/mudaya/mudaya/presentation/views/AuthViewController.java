package com.mudaya.mudaya.presentation.views;

import com.mudaya.mudaya.domain.enums.Sexo;
import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.managers.SessionManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthViewController {

    private final SessionManager sessionManager;

    public AuthViewController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /** Login: si ya hay sesión, redirige a / */
    @GetMapping("/login")
    public String loginPage(HttpSession session,
                            @RequestParam(value="logout", required=false) String logout,
                            Model model) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "Has cerrado sesión correctamente");
        }
        return "login";
    }

    /** Procesar login con tu SessionManager */
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }
        try {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            User loggedUser = sessionManager.login(user);
            session.setAttribute("currentUser", loggedUser);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("loginError", e.getMessage());
            return "login";
        }
    }

    /** Register: si ya hay sesión, redirige a / */
    @GetMapping("/register")
    public String registerPage(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }
        return "register";
    }

    /** Procesar registro */
    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String telephoneNumber,
                           @RequestParam String DNI,
                           @RequestParam Sexo sexo,
                           HttpSession session,
                           Model model) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }
        try {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setPhoneNumber(telephoneNumber);
            newUser.setDni(DNI);
            newUser.setSexo(sexo);

            User registeredUser = sessionManager.register(newUser);
            session.setAttribute("currentUser", registeredUser);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("registerError", e.getMessage());
            return "register";
        }
    }

    /** Logout: invalida la sesión y redirige a login?logout */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
