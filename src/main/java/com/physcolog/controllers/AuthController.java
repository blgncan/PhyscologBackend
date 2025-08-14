package com.physcolog.controllers;
import com.physcolog.entities.User;
import com.physcolog.security.service.JwtTokenUtil;
import com.physcolog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String userName = credentials.get("userName");
        String password = credentials.get("password");

        Optional<User> userOpt = userService.authenticate(userName, password);
        if (userOpt.isPresent()) {
            String token = jwtTokenUtil.generateToken(userName);
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Geçersiz kullanıcı adı veya şifre"));
    }
}
