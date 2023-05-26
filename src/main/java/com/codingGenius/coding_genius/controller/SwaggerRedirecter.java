package com.codingGenius.coding_genius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/api/usage")
class SwaggerRedirecter {
    @GetMapping
    public String api() { return "redirect:/swagger-ui/index.html"; }
}

