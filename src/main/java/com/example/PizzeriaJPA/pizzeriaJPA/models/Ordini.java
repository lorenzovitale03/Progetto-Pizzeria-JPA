package com.example.PizzeriaJPA.pizzeriaJPA.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ordini")

//Un ordine ha: id, data, cliente, lista di pizze ordinate, stato (in preparazione, consegnato, annullato).
public class Ordini {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clienti cliente;

    @ManyToMany//poichè piu' ordini possono appartenere a piu' clienti.
    @JoinTable(name = "ordine_pizza",
            joinColumns = @JoinColumn(name = "ordine_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Pizza> pizzeOrdinate;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false)
    private StatoOrdine stato;

}
