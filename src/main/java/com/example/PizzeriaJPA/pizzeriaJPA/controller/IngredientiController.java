package com.example.PizzeriaJPA.pizzeriaJPA.controller;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Ingredienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.services.IngredientiService;
import com.example.PizzeriaJPA.pizzeriaJPA.services.PizzaService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredienti")
public class IngredientiController {

    @Autowired IngredientiService ingredientiService;


    @GetMapping
    public List<Ingredienti> getListIngredients(){
        return ingredientiService.viewIngredientsList();
    }

    @PostMapping
    public Ingredienti saveIngredient(@RequestBody Ingredienti ingredienti){
        return ingredientiService.createIngredients(ingredienti);
    }

    //se l'ingrediente non è presente nel db non posso non posso aggiornarlo
    @PutMapping("{id}")
    public ResponseEntity<Ingredienti> updateIngredientById(@PathVariable Long id, @RequestBody Ingredienti ingredienti){
        //mi vado a recuperare l'id dell'ingrediente
        Ingredienti verificaIngrediente = ingredientiService.getIngredientById(id);

        //se è presente nel db lo vado ad aggiornare
        if(verificaIngrediente != null){
             ingredientiService.updateIngredientsById(id,ingredienti);
             return ResponseEntity.ok(verificaIngrediente);
        }else{
            throw new EntityNotFoundException("Ingrediente non trovato");
        }
    }

    //Se l'ingrediente non esiste nel database,di conseguenza non è eliminabile quindi gestisco l'eccezione
    @DeleteMapping("{id}/delete")
    public void deleteIngredientsById(@PathVariable Long id){
        //mi vado a recuperare l'id dell'ingrediente
        Ingredienti verifica = ingredientiService.getIngredientById(id);

        //se esiste lo elimino
        if(verifica != null){
            ingredientiService.deleteIngredientsById(id);
        }else{
            throw new EntityExistsException("Ingrediente inesistente quindi non eliminabile");
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<Ingredienti> viewIngredientById(@PathVariable Long id){
        Ingredienti recupero = ingredientiService.getIngredientById(id);

        if(recupero != null){
             return ResponseEntity.ok(recupero);
        }else{
            throw new EntityExistsException("ingrediente inesistente quindi non visualizzabile");
        }

    }
}
