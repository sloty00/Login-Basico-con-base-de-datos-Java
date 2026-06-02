package com.example.projectlogin.controller;

import com.example.projectlogin.dto.LoginRequest;
import com.example.projectlogin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        
        boolean loginExitoso = usuarioService.autenticar(request.getUsername(), request.getPassword());

        if (loginExitoso) {
            // Retorna un estado HTTP 200 OK
            return ResponseEntity.ok("¡Login exitoso! Acceso concedido.");
        } else {
            // Retorna un estado HTTP 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas. Acceso denegado.");
        }
    }
}