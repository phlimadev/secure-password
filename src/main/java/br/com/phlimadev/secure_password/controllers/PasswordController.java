package br.com.phlimadev.secure_password.controllers;

import br.com.phlimadev.secure_password.dtos.PasswordDTO;
import br.com.phlimadev.secure_password.services.PasswordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
public class PasswordController {
    @Autowired
    private PasswordService passwordService;

    @PostMapping
    public ResponseEntity validatePassword(@RequestBody @Valid PasswordDTO password) {
        return passwordService.validatePassword(password);
    }
}
