package com.sharkend.jwttemplate;

import com.sharkend.jwttemplate.Dto.Auth.RegisterDto;
import com.sharkend.jwttemplate.Entity.User.Role;
import com.sharkend.jwttemplate.Service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.sharkend.jwttemplate.Entity.User.Role.ADMIN;
import static com.sharkend.jwttemplate.Entity.User.Role.MANAGER;

@SpringBootApplication
public class JwttemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwttemplateApplication.class, args);
	}

	//for testing:
	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service) {
		return args -> {
			var admin = RegisterDto.builder()
					.firstName("Admin")
					.lastName("admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("admin token : "+ service.register(admin).getToken());
			var manager = RegisterDto.builder()
					.firstName("Manager")
					.lastName("manager")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("manager token : "+ service.register(manager).getToken());
		};
	}
}
