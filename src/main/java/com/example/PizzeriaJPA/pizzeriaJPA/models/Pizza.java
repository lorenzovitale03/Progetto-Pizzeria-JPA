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
    @Column (nullable = false)
    private Double prezzo;
    @Column(nullable = false)
    private Boolean disponibile;

    @ManyToMany
    @JoinTable(name = "pizza_ingredienti",
    joinColumns = @JoinColumn(name = "pizza_id"),
    inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    private List<Ingredienti> ingredienti;
}
