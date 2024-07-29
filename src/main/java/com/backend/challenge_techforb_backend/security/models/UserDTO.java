package com.backend.challenge_techforb_backend.security.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String nombre;
    private String apellido;
    private String email;
    private String password; 
    
}
