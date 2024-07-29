package com.backend.challenge_techforb_backend;

import com.backend.challenge_techforb_backend.security.handlerException.InvalidEmailException;
import com.backend.challenge_techforb_backend.security.handlerException.InvalidPasswordException;

import com.backend.challenge_techforb_backend.security.models.UserDTO;
import com.backend.challenge_techforb_backend.security.models.Usuario;
import com.backend.challenge_techforb_backend.security.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.naming.InvalidNameException;

@SpringBootApplication
public class ChallengeTechforbBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeTechforbBackendApplication.class, args);
	}


	/* @Bean
	public CommandLineRunner crearUsuario(UsuarioService usuarioService) {
		return args -> {
			UserDTO userDto = new UserDTO();
			userDto.setNombre("Pedro");
			userDto.setApellido("Navaja");
			userDto.setEmail("pe@email.com");
			userDto.setPassword("123456");

			try {
				Usuario usuario = usuarioService.registerUsuario(userDto);
				System.out.println("Usuario creado: " + usuario.toString());
			} catch (InvalidNameException | InvalidEmailException | InvalidPasswordException e) {
				System.out.println("Error al crear el usuario: " + e.getMessage());
			}
		};
	}
 */

}
