package com.example.biblioteca;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.biblioteca.persistance.entities.RolEntity;
import com.example.biblioteca.persistance.entities.UserEntity;
import com.example.biblioteca.persistance.repository.RolRepository;
import com.example.biblioteca.persistance.repository.UserRepository;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RolRepository rolRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {

			RolEntity rolAdmin = RolEntity.builder().name("ADMIN").build();
			RolEntity rolUser = RolEntity.builder().name("USER").build();
			RolEntity rolInvited = RolEntity.builder().name("INVITED").build();

			rolRepository.save(rolAdmin);
			rolRepository.save(rolUser);
			rolRepository.save(rolInvited);

			RolEntity rolAdminsaved = rolRepository.findByName("ADMIN")
				.orElseThrow(() -> new RuntimeException("Rol no encontrado"));

			UserEntity userEntity = UserEntity.builder()
			.email("test1@gmail.com")
			.username("test1")
			.password(passwordEncoder.encode("123"))
			.status('A')
			.roles(Set.of(
				rolAdminsaved))
			.build();

			UserEntity userEntity2 = UserEntity.builder()
			.email("test2@gmail.com")
			.username("test2")
			.password(passwordEncoder.encode("123"))
			.status('A')
			.roles(Set.of(
				rolAdminsaved))
			.build();

			UserEntity userEntity3 = UserEntity.builder()
			.email("test3@gmail.com")
			.username("test3")
			.password(passwordEncoder.encode("123"))
			.status('A')
			.roles(Set.of(
				rolAdminsaved))
			.build();

			userRepository.save(userEntity);
			userRepository.save(userEntity2);
			userRepository.save(userEntity3);
		};
	}

}
