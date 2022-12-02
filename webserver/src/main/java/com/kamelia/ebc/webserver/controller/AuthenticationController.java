package com.kamelia.ebc.webserver.controller;

import com.kamelia.ebc.webserver.dto.CredentialsDTO;
import com.kamelia.ebc.webserver.dto.LoginDTO;
import com.kamelia.ebc.webserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<LoginDTO> login(@RequestBody CredentialsDTO credentialsDTO) throws RemoteException {
        var sessionId = userService.login(credentialsDTO.username(), credentialsDTO.password());
        return ResponseEntity.ok(new LoginDTO(sessionId));
    }

    @PutMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CredentialsDTO credentialsDTO) throws RemoteException {
        if (userService.register(credentialsDTO.username(), credentialsDTO.password())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LoginDTO loginDTO) throws RemoteException {
        userService.logout(loginDTO.sessionId());
        return ResponseEntity.ok().build();
    }

}