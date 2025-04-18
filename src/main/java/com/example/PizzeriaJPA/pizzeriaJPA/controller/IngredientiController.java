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

    @PutMapping("{id}")
    public ResponseEntity<String> updateIngredientById(@PathVariable Long id, @RequestBody Ingredienti ingredienti){
        ingredientiService.updateIngredientsById(id,ingredienti);
        return ResponseEntity.ok("Ingrediente aggiornato con successo");
    }

    //Se l'ingrediente non esiste nel database,di conseguenza non è eliminabile quindi gestisco l'eccezione
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteIngredientsById(@PathVariable Long id){
        ingredientiService.deleteIngredientsById(id);
        return ResponseEntity.ok("Ingrediente numero: " + id + " eliminato con successo");
    }


    @GetMapping("{id}")
    public ResponseEntity<Ingredienti> viewIngredientById(@PathVariable Long id){
       Ingredienti visualizzaIngrediente = ingredientiService.getIngredientById(id);
       return ResponseEntity.ok(visualizzaIngrediente);

    }
}
