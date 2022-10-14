package com.gabinishimwe.springdatajpa;

import com.gabinishimwe.springdatajpa.entities.Role;
import com.gabinishimwe.springdatajpa.entities.User;
import com.gabinishimwe.springdatajpa.entities.UserModel;
import com.gabinishimwe.springdatajpa.repositories.RoleRepository;
import com.gabinishimwe.springdatajpa.repositories.UserRepository;
import com.gabinishimwe.springdatajpa.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
		return args -> {
			UserModel userModel1 = new UserModel(
					"gabon",
					"ishimwe",
					"gam@gmail.com",
					"1234"
			);

			Role role1 = new Role(
					null,
					"ADMIN"
			);
			Role role2 = new Role(
					null,
					"NORMAL"
			);
			userService.createUser(userModel1);
			roleRepository.save(role1);
			roleRepository.save(role2);
		};
	}

}
