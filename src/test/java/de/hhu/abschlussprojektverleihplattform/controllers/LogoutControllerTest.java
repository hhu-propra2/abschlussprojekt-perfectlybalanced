package de.hhu.abschlussprojektverleihplattform.controllers;


import de.hhu.abschlussprojektverleihplattform.service.CookieUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LogoutControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testcontrolleristhere() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void cookieOverwrite() throws Exception{
        mockMvc.perform(get("/logout").cookie(new Cookie(CookieUserService.cookieName,"1")))
                .andExpect(MockMvcResultMatchers.cookie().value(CookieUserService.cookieName,LogoutController.logoutid));
    }
}
