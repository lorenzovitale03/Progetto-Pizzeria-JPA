package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Ingredienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.IngredientiRepository;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//Creazione, modifica, eliminazione e visualizzazione di ingredienti.

public class IngredientiService {

    @Autowired IngredientiRepository ingredientiRepository;
    @Autowired PizzaRepository pizzaRepository;

    public Ingredienti createIngredients(Ingredienti ingredienti){
        return ingredientiRepository.save(ingredienti);
    }

    public Ingredienti updateIngredientsById(Long id, Ingredienti ingredienti){
        //recupero l'ingrediente  tramite id
        return ingredientiRepository.findById(id).map(modificaIngredienti ->{
            if(ingredienti.getNome() != null && ingredienti.getCalorie() != null){
                modificaIngredienti.setNome(ingredienti.getNome());
                modificaIngredienti.setCalorie(ingredienti.getCalorie());
            }
            //salvo  l'ingrediente modificato nel db
            return ingredientiRepository.save(modificaIngredienti);
        }).orElseThrow(() -> new EntityNotFoundException("Ingrediente numero: " + id + " non trovato"));
    }

    public void deleteIngredientsById(Long id){
        Ingredienti ingredienti = ingredientiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente non trovato"));
        List<Pizza> pizzeConIngrediente = pizzaRepository.findAllByIngredienti_Id(id);

        for(Pizza pizza : pizzeConIngrediente){
                pizza.getIngredienti().remove(ingredienti);
                pizzaRepository.save(pizza);
            }

        ingredientiRepository.delete(ingredienti);
    }

    public Ingredienti getIngredientById(Long id){
        return ingredientiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente non trovato"));
    }

    public List<Ingredienti> viewIngredientsList(){
        return ingredientiRepository.findAll();
    }
}
