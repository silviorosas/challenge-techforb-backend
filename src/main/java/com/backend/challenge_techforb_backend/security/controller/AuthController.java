package com.backend.challenge_techforb_backend.security.controller;



import javax.naming.InvalidNameException;

import com.backend.challenge_techforb_backend.security.models.LoginDto;
import com.backend.challenge_techforb_backend.security.models.UserDTO;
import com.backend.challenge_techforb_backend.security.models.UserResponse;
import com.backend.challenge_techforb_backend.security.models.Usuario;
import com.backend.challenge_techforb_backend.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://techforb.netlify.app")
public class AuthController {

    @Autowired
    UsuarioService userService;

    @PostMapping
    public ResponseEntity<Usuario> register(@RequestBody UserDTO userDTO) throws InvalidNameException{
        Usuario usuario = userService.registerUsuario(userDTO);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
    
    
}
