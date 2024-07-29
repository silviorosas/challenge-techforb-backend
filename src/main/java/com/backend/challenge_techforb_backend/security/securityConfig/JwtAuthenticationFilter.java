package com.backend.challenge_techforb_backend.security.securityConfig;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
    throws ServletException, IOException {
try {
    String token = getTokenFromRequest(request);
    if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
        String username = jwtTokenProvider.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(request, response);
} catch (JwtTokenProvider.JwtTokenException ex) {
    // Establecer el código de estado y enviar el mensaje de la excepción al cliente
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.getWriter().write("{\"error\": \"" + ex.getMessage() + "\"}");
}
}

private String getTokenFromRequest(HttpServletRequest request) {
String bearerToken = request.getHeader("Authorization");
if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
    return bearerToken.substring(7);
}
return null;
}

    
    
}
