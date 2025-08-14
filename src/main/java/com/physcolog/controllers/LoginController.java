package com.physcolog.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Data
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 📌 `login.html` template'ini döndürüyor
    }
}
