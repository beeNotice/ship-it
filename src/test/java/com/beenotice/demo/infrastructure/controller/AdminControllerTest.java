package com.beenotice.demo.infrastructure.controller;

import com.beenotice.demo.domain.model.Decision;
import com.beenotice.demo.domain.model.SanityCheck;
import com.beenotice.demo.domain.spi.SanityCheckInventory;
import com.beenotice.demo.infrastructure.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
@Import(SecurityConfig.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SanityCheckInventory sanityCheckInventory;

    @Test
    void scenarios_returnsUnauthorized_whenNoCredentials() throws Exception {
        mockMvc.perform(get("/admin/scenarios"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void scenarios_returnsOk_whenAdmin() throws Exception {
        when(sanityCheckInventory.findAll()).thenReturn(List.of(
                new SanityCheck("ctx", "question?",
                        new Decision("A", "consequence A"),
                        new Decision("B", "consequence B"))
        ));

        mockMvc.perform(get("/admin/scenarios").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/scenarios"))
                .andExpect(model().attributeExists("scenarios"));
    }
}
