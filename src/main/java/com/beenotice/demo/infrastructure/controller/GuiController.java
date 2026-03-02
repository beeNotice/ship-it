package com.beenotice.demo.infrastructure.controller;

import com.beenotice.demo.domain.api.SanityCheckRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("checkIndex")
public class GuiController {

    private final SanityCheckRunner sanityCheckRunner;

    public GuiController(SanityCheckRunner sanityCheckRunner) {
        this.sanityCheckRunner = sanityCheckRunner;
    }

    @ModelAttribute("checkIndex")
    public Integer initCheckIndex() {
        return 0;
    }

    @GetMapping("/")
    public String sanityCheck(@ModelAttribute("checkIndex") Integer checkIndex, Model model) {
        model.addAttribute("sanityCheck", sanityCheckRunner.getCheckAt(checkIndex));
        model.addAttribute("checkIndex", checkIndex + 1);
        return "sanity-check";
    }
}
