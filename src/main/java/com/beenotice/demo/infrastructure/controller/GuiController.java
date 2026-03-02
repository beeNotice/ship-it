package com.beenotice.demo.infrastructure.controller;

import com.beenotice.demo.infrastructure.repository.HelloRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuiController {

  private final HelloRepository helloRepository;

  public GuiController(HelloRepository helloRepository) {
    this.helloRepository = helloRepository;
  }

  @GetMapping("/")
  public String greeting(Model model) {
    model.addAttribute("hello", helloRepository.getNextHello());
    return "hello";
  }

}
