package com.example.PizzeriaJPA.pizzeriaJPA.repositories;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Ingredienti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientiRepository extends JpaRepository<Ingredienti, Long> {
}
