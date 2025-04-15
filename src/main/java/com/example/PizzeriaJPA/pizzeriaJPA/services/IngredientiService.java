package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Ingredienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.IngredientiRepository;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.PizzaRepository;
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
            if(ingredienti.getNome() != null) modificaIngredienti.setNome(ingredienti.getNome());
            if(ingredienti.getCalorie() != null) modificaIngredienti.setCalorie(ingredienti.getCalorie());
            //salvo  l'ingrediente modificato nel db
            return ingredientiRepository.save(modificaIngredienti);
        }).orElse(null);
    }

    public void deleteIngredientsById(Long id){
        Ingredienti ingredienti = ingredientiRepository.findById(id).orElse(null);

        List<Pizza> pizzeConIngrediente = pizzaRepository.findAllByIngredienti_Id(id);
        for(Pizza pizza : pizzeConIngrediente){
            pizza.getIngredienti().remove(ingredienti);
            pizzaRepository.save(pizza);
        }

        ingredientiRepository.deleteById(id);
    }

    public Ingredienti getIngredientById(Long id){
        return ingredientiRepository.findById(id).orElse(null);
    }

    public List<Ingredienti> viewIngredientsList(){
        return ingredientiRepository.findAll();
    }
}
