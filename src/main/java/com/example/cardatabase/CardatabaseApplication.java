package com.example.cardatabase;

import com.example.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class CardatabaseApplication {

    private static Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    @Autowired
    private CarRepository repository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UsersRepository usersRepository;

    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);

    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Owner ownerFirst = new Owner("Alex", "Varfolomeev");
            Owner ownerSecond = new Owner("Sergey", "Khil");
            Owner ownerThird = new Owner("Vasya", "Pavlov");
            ownerRepository.saveAll(Arrays.asList(ownerFirst, ownerSecond, ownerThird));
            repository.save(new Car("Bmw", "m4", "black", "335AOB", 1980, 33000, ownerFirst));
            repository.save(new Car("Bmw", "asd", "asd", "asd", 1990, 33000, ownerFirst));
            repository.save(new Car("Nissan", "Leaf", "red", "228BB", 1995, 22200, ownerSecond));
            repository.save(new Car("Bmw", "m4", "black", "335AOB", 1985, 33000, ownerThird));
            usersRepository.save(new Users(1L,"qweqwe","$2a$04$U.5noUJgefHOJCCYZLUyhuvuSaptOKAxwzENLKL3YS76Dca.K0ita","ADMIN"));
            usersRepository.save(new Users(2L,"user","$2a$04$.vRC3xQEflWACZR5FvQyseb3VGNaBVYtL4lxMwMsrYnmR7nCmj.5W","USER"));
        };
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
