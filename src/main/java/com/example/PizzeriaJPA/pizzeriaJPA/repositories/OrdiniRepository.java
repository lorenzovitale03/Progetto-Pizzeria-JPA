package com.example.PizzeriaJPA.pizzeriaJPA.repositories;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Ordini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdiniRepository extends JpaRepository<Ordini, Long> {
}
