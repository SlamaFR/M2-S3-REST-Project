package com.kamelia.ebc.webserver.controller;

import com.kamelia.ebc.webserver.dto.LoginDTO;
import com.kamelia.ebc.webserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;
import java.util.UUID;

@Controller
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(
        String username,
        String password
    ) throws RemoteException {
        var sessionId = userService.login(username, password);
        return ResponseEntity.ok(new LoginDTO(sessionId));
    }

    @PutMapping("/register")
    public ResponseEntity<Void> register(
        String username,
        String password
    ) throws RemoteException {
        if (userService.register(username, password)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
        String sessionId
    ) throws RemoteException {
        userService.logout(UUID.fromString(sessionId));
        return ResponseEntity.ok().build();
    }

}
