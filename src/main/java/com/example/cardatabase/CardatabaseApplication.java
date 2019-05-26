package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CardatabaseApplication {

	private static Logger logger= LoggerFactory.getLogger(CardatabaseApplication.class);

	@Autowired
	private CarRepository repository;

	@Autowired
	private OwnerRepository ownerRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Hello spring boot");

	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Owner ownerFirst=new Owner("Alex", "Varfolomeev");
			Owner ownerSecond=new Owner("Sergey", "Khil");
			ownerRepository.saveAll(Arrays.asList(ownerFirst,ownerSecond));
			repository.save(new Car("Bmw","m4","black", "335AOB", 1980,33000,ownerFirst));
			repository.save(new Car("Bmw","asd","asd", "asd", 1990,33000,ownerFirst));
			repository.save(new Car("Nissan","Leaf","red", "228BB", 1995,22200,ownerSecond));
			repository.save(new Car("Bmw","m4","black", "335AOB", 1985,33000,ownerFirst));
		};
	}

}
