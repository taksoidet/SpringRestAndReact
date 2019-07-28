package com.example.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.cardatabase.web.CarController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
public class CardatabaseApplicationTests {


	@Autowired
	private CarController carController;



	@Test
	public void contextLoads() {
		assertThat(carController).isNotNull();
	}



}
