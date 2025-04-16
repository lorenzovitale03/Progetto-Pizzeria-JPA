package com.example.PizzeriaJPA.pizzeriaJPA.repositories;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Clienti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientiRepository extends JpaRepository<Clienti, Long> {
}
