package com.example.cardatabase.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {


    List<Car> findByBrand(String brand);

    List<Car> findByColor(String color);

    List<Car> findByYear(String year);

    List<Car> findByBrandOrderByYearAsc(String brand);

}
