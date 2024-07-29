package com.backend.challenge_techforb_backend.security.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String responseCode;
    private String responseMessage;   
    
}
