package com.rei1997.vault;

import java.time.LocalDateTime;


import com.rei1997.vault.model.entity.User;
import com.rei1997.vault.model.repository.UserRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class VaultApplication {

	public static void main(String[] args) {
			SpringApplication.run(VaultApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepo userDao, MongoTemplate mongotemplate){
		return args -> {
			String email ="test456@123.ccc";
			User user = new User(
				email,"test","test",0123456,1
			);


			userDao.findUserByEmail(email)
				.ifPresentOrElse(users ->{
					System.out.println("already exists");
					System.out.println(users);
		
				}, ()->{
					userDao.insert(user);	
				});
		};
	}
	
}