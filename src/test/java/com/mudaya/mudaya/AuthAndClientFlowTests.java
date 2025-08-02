package com.mudaya.mudaya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.enums.Sexo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthAndClientFlowTests {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    private User registro;
    private String rawPassword = "MiPas$123";

    @BeforeEach
    void setUp() {
        registro = new User();
        registro.setName("Test");
        registro.setSurname("User");
        // Genera algo como test3456@test.com (siempre < 50 chars)
        int randomSuffix = ThreadLocalRandom.current().nextInt(1000, 10000);
        String uniqueEmail = String.format("test%d@test.com", randomSuffix);
        registro.setEmail(uniqueEmail);
        registro.setPassword(rawPassword);
        registro.setPhoneNumber("12345678");
        registro.setDni("98765432");
        registro.setSexo(Sexo.M);
    }

    @Test
    void registerThenLoginAndCreateClient() throws Exception {
        // 1) Registro
        mvc.perform(post("/register")
                        .param("email",           registro.getEmail())
                        .param("password",        registro.getPassword())
                        .param("name",            registro.getName())
                        .param("surname",         registro.getSurname())
                        .param("telephoneNumber", registro.getPhoneNumber())
                        .param("DNI",             registro.getDni())
                        .param("sexo",            registro.getSexo().name())
                )
                .andExpect(status().is3xxRedirection());

        // 2) Login y capturo la sesión “real”
        MvcResult loginResult = mvc.perform(post("/login")
                        .param("email",    registro.getEmail())
                        .param("password", registro.getPassword())
                )
                .andExpect(status().is3xxRedirection())
                .andReturn();
        MockHttpSession session = (MockHttpSession) loginResult.getRequest().getSession(false);

        // 3) GET al formulario de nuevo cliente
        mvc.perform(get("/clients/new").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nuevo Cliente")));

        // 4) POST de creación de cliente (incluyendo todos los campos @NotNull)
        mvc.perform(post("/clients")
                        .session(session)
                        .param("name",        "Juan")
                        .param("surname",     "Perez")
                        .param("phoneNumber", "5551234")
                        .param("dni",         "87654321")
                        .param("sexo",        "M")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(redirectedUrl("/clients"));
    }

}
