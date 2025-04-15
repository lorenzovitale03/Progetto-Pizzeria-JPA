package com.example.PizzeriaJPA.pizzeriaJPA.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ingredienti")

//Ogni ingrediente ha: id, nome, calorie.
public class Ingredienti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer calorie;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredienti")
    private List<Pizza> pizze;
}
