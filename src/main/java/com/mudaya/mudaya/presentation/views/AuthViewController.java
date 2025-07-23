package com.mudaya.mudaya.presentation.views;

import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.enums.Sexo;
import com.mudaya.mudaya.domain.managers.SessionManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthViewController {

    private final SessionManager sessionManager;

    @Autowired
    public AuthViewController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // carga login.html desde templates
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User loggedUser = sessionManager.login(user);
        session.setAttribute("currentUser", loggedUser);

        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String telephoneNumber,
                           @RequestParam String DNI,
                           @RequestParam Sexo sexo,
                           HttpSession session) {
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
    }
}
