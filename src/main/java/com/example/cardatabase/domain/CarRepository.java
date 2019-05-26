package com.example.cardatabase.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByBrand(String brand);
    List<Car> findByColor(String color);
    List<Car> findByYear(String year);
    List<Car> findByBrandOrderByYearAsc(String brand);

}
