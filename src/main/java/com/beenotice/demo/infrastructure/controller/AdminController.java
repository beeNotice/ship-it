package com.beenotice.demo.infrastructure.controller;

import com.beenotice.demo.domain.spi.SanityCheckInventory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final SanityCheckInventory sanityCheckInventory;

    AdminController(SanityCheckInventory sanityCheckInventory) {
        this.sanityCheckInventory = sanityCheckInventory;
    }

    @GetMapping("/admin/scenarios")
    public String scenarios(Model model) {
        model.addAttribute("scenarios", sanityCheckInventory.findAll());
        return "admin/scenarios";
    }
}
