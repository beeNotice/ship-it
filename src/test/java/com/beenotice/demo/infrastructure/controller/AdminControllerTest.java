package com.beenotice.demo.infrastructure.controller;

import com.beenotice.demo.domain.model.Decision;
import com.beenotice.demo.domain.model.SanityCheck;
import com.beenotice.demo.domain.spi.SanityCheckInventory;
import com.beenotice.demo.infrastructure.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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
    void adminScenarios_withoutCredentials_returns401() throws Exception {
        mockMvc.perform(get("/admin/scenarios"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminScenarios_withAdminRole_returns200() throws Exception {
        when(sanityCheckInventory.findAll()).thenReturn(List.of(
                new SanityCheck("context", "question?",
                        new Decision("Option A", "consequence A"),
                        new Decision("Option B", "consequence B"))
        ));

        mockMvc.perform(get("/admin/scenarios").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/scenarios"))
                .andExpect(model().attributeExists("scenarios"));
    }

    @Test
    void adminScenarios_withoutAdminRole_returns403() throws Exception {
        mockMvc.perform(get("/admin/scenarios").with(user("user").roles("USER")))
                .andExpect(status().isForbidden());
    }
}
