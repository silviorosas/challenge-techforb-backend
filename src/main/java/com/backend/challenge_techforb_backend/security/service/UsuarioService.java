package com.backend.challenge_techforb_backend.security.service;

import com.backend.challenge_techforb_backend.security.handlerException.InvalidCredentialsException;
import com.backend.challenge_techforb_backend.security.handlerException.InvalidEmailException;
import com.backend.challenge_techforb_backend.security.handlerException.InvalidNameEx;
import com.backend.challenge_techforb_backend.security.handlerException.InvalidPasswordException;
import com.backend.challenge_techforb_backend.security.models.*;
import com.backend.challenge_techforb_backend.security.repository.UsuarioRepository;
import com.backend.challenge_techforb_backend.security.securityConfig.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    //para crear el ususrio vinculado al user details
    public Usuario registerUsuario(UserDTO userDto) throws InvalidNameException {
        if (!isNameValid(userDto.getNombre())) {
            throw new InvalidNameEx("El nombre es obligatorio, debe tener al menos tres caracteres y solo letras");
        }

        if (!isNameValid(userDto.getNombre())) {
            throw new InvalidNameException("El nombre es obligatorio, debe tener al menos tres caracteres y solo letras");
        }

        if (!isNameValid(userDto.getApellido())) {
            throw new InvalidNameEx("El apellido es obligatorio, debe tener al menos tres caracteres y solo letras");
        }

        if (!isEmailValid(userDto.getEmail())) {
            throw new InvalidEmailException("El email es obligatorio y debe tener un formato correcto");
        }

        if (usuarioRepository.existsByEmail(userDto.getEmail())) {
            throw new InvalidEmailException("El email ya está registrado");
        }

        if (!isPasswordValid(userDto.getPassword())) {
            throw new InvalidPasswordException("El password es obligatorio y debe tener al menos cuatro caracteres");
        }

        Usuario usuario = Usuario.builder()
                .nombre(userDto.getNombre())
                .apellido(userDto.getApellido())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        return usuarioRepository.save(usuario);
    }

    private boolean isNameValid(String name) {
        return name != null && name.matches("^[A-Za-z]{3,}$");
    }

    private boolean isEmailValid(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.length() >= 4;
    }


    public UserResponse login(LoginDto loginDto) {
        if (loginDto.getEmail() == null || loginDto.getEmail().isEmpty()) {
            throw new InvalidEmailException("El email es obligatorio y debe tener un formato correcto");
        }

        if (loginDto.getPassword() == null || loginDto.getPassword().isEmpty()) {
            throw new InvalidPasswordException("El password es obligatorio y debe tener al menos cuatro caracteres");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

            return UserResponse.builder()
                    .responseCode("Login Success")
                    .responseMessage(jwtTokenProvider.generateToken(authentication))
                    .build();
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Las credenciales no coinciden");
        }
    }

}



