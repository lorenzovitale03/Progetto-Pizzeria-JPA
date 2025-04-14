package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//Creazione, modifica, eliminazione e visualizzazione di pizze.

public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        //recupero la pizza tramite id
        return pizzaRepository.findById(id).map(modificaPizza -> {
            if (pizza.getNome() != null) modificaPizza.setNome(pizza.getNome());
            if (pizza.getDescrizione() != null) modificaPizza.setDescrizione(pizza.getDescrizione());
            if (pizza.getPrezzo() != null) modificaPizza.setPrezzo(pizza.getPrezzo());
            if (pizza.getDisponibile() != null) modificaPizza.setDisponibile(pizza.getDisponibile());

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
}
