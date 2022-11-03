package com.fivetwenty.piggyback;

import com.fivetwenty.piggyback.model.IUser;
import com.fivetwenty.piggyback.model.User;
import com.fivetwenty.piggyback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiggybackApplication implements CommandLineRunner {

	//Test mongo connections.
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PiggybackApplication.class, args);
		System.out.println("Starting Application");


	}


	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("","Snehil Verma",0.0f, IUser.UserType.DRIVER);
		User user2 = new User("","Piusha G",0.0f, IUser.UserType.PASSENGER);
		userRepository.save(user1);
		userRepository.save(user2);
		System.out.println("Users saved");

		//there are many ways to do this and only for TEST we are doing this.
//		System.out.println(userRepository.findUserByName("Snehil Verma").getUserType());
//		System.out.println(userRepository.findUserByName("Piusha G").getUserType());

	}
}
