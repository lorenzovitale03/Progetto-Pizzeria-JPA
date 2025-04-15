package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Ingredienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.IngredientiRepository;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//Creazione, modifica, eliminazione e visualizzazione di pizze.

public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired IngredientiRepository ingredientiRepository;

    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        //recupero la pizza tramite id
        return pizzaRepository.findById(id).map(modificaPizza -> {
            if (pizza.getPrezzo() != null) modificaPizza.setPrezzo(pizza.getPrezzo());
            if (pizza.getDisponibile() != null) modificaPizza.setDisponibile(pizza.getDisponibile());
            if(pizza.getNome() !=  null) modificaPizza.setNome(pizza.getNome());
            //salvo la pizza modificata nel db
            return pizzaRepository.save(modificaPizza);
        }).orElse(null);
    }

    public void deletePizza(Long id){
         pizzaRepository.deleteById(id);
    }

    public List<Pizza> viewListPizza(Pizza pizza){
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaById(Long id){
        return pizzaRepository.findById(id).orElse(null);
    }

    public Pizza addIngredientsToPizza(Long pizzaId, Long ingredienteId){
        //recupero l'id della pizza
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        //recupero gli ingredienti della pizza
        Ingredienti ingredienti = ingredientiRepository.findById(ingredienteId).orElse(null);

        if(pizza != null && ingredienti != null ){
            //se entrambi sono presenti nel db, verifica se l'ingrediente è gia' stato assegnato alla pizza
            if(!pizza.getIngredienti().contains(ingredienti)){
                //aggiungo l'ingrediente alla pizza
                pizza.getIngredienti().add(ingredienti);
                //salvo la pizza e gli ingredienti
                pizzaRepository.save(pizza);
                ingredientiRepository.save(ingredienti);
            }
        }


        return pizza;
    }
}
