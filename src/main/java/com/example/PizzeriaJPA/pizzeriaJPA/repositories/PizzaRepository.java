package com.example.PizzeriaJPA.pizzeriaJPA.repositories;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findAllByIngredienti_Id(Long ingredienteId);

}
