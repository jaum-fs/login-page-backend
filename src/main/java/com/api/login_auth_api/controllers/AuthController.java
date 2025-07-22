package com.api.login_auth_api.controllers;

import com.api.login_auth_api.domain.User;
import com.api.login_auth_api.dto.LoginRequestDTO;
import com.api.login_auth_api.dto.RegisterRequestDTO;
import com.api.login_auth_api.dto.ResponseDTO;
import com.api.login_auth_api.infra.security.TokenService;
import com.api.login_auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        User user = this.userRepository.findByEmail(loginRequestDTO.email()).orElseThrow(()->new RuntimeException("User not found"));
        if(passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())){
            return ResponseEntity.ok(new ResponseDTO(user.getName(),tokenService.generateToken(user)));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        Optional<User> optUser = this.userRepository.findByEmail(registerRequestDTO.email());
        if(optUser.isEmpty()){
            val user = userRepository.save(new User(null,registerRequestDTO.name(), registerRequestDTO.email(), passwordEncoder.encode(registerRequestDTO.password())));
            return ResponseEntity.ok(new ResponseDTO(user.getName(),tokenService.generateToken(user)));
        }
        return ResponseEntity.badRequest().build();
    }
}
