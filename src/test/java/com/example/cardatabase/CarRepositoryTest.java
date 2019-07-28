package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    private EntityManager entityManager;
    @Test
    public void saveCar(){
        Car car=new Car("bmw","m5", "black","DAO",1995,200,new Owner("kek","kek"));
//        carRepository.save(car);
        entityManager.persist(car);
//        long id=car.getId();
//        assertThat(Long.valueOf(id)).isNotNull();
//        Car car1=carRepository.findById(car.getId()).get();
//        Assert.assertEquals(car,car1);
    }

}