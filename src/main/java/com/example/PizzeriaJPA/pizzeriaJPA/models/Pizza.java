package com.example.PizzeriaJPA.pizzeriaJPA.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pizze")

//Ogni pizza ha: id, nome, descrizione, prezzo, disponibile, e una lista di ingredienti.
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private String descrizione;
    @Column (nullable = false)
    private Double prezzo;
    @Column(nullable = false)
    private Boolean disponibile;
}
