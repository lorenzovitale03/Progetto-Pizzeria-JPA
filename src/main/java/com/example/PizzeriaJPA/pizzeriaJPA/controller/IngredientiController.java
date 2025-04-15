package com.example.PizzeriaJPA.pizzeriaJPA.controller;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Ingredienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.services.IngredientiService;
import com.example.PizzeriaJPA.pizzeriaJPA.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Ingredienti updateIngredientById(@PathVariable Long id,@RequestBody Ingredienti ingredienti){
        return ingredientiService.updateIngredientsById(id,ingredienti);
    }

    @DeleteMapping("{id}/delete")
    public void deleteIngredientsById(@PathVariable Long id){
        ingredientiService.deleteIngredientsById(id);
    }

    @GetMapping("{id}")
    public Ingredienti viewIngredientById(@PathVariable Long id){
        return ingredientiService.getIngredientById(id);
    }
}
