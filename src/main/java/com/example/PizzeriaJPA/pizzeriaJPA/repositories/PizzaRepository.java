package com.example.PizzeriaJPA.pizzeriaJPA.repositories;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
