package de.hhu.abschlussprojektverleihplattform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringSecurityBackendTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {
    }

    @Test
    @WithUserDetails("sarah")
    public void testSecurityWorks() throws Exception {
        mvc.perform(
                get("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));

    }

}