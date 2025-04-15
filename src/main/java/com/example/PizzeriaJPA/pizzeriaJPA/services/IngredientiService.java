package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Ingredienti;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.IngredientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//Creazione, modifica, eliminazione e visualizzazione di ingredienti.

public class IngredientiService {

    @Autowired IngredientiRepository ingredientiRepository;

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
        ingredientiRepository.deleteById(id);
    }

    public Ingredienti getIngredientById(Long id){
        return ingredientiRepository.findById(id).orElse(null);
    }

    public List<Ingredienti> viewIngredientsList(){
        return ingredientiRepository.findAll();
    }
}
