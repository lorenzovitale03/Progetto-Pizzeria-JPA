package com.example.PizzeriaJPA.pizzeriaJPA.controller;

import com.example.PizzeriaJPA.pizzeriaJPA.exception.MyExceptionHandler;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Ingredienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.services.IngredientiService;
import com.example.PizzeriaJPA.pizzeriaJPA.services.PizzaService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.border.LineBorder;
import java.util.List;

@RestController
@RequestMapping("/api/pizze")
public class PizzaController {

    @Autowired PizzaService pizzaService;
    @Autowired IngredientiService ingredientiService;

    @PostMapping
    public Pizza getPizza(@RequestBody Pizza pizza){
        return pizzaService.savePizza(pizza);
    }

    @PutMapping("{id}")
    public ResponseEntity<Pizza> aggiornoPizza(@PathVariable Long id, @RequestBody Pizza pizza){
        Pizza pizzAggiorna = pizzaService.updatePizza(id, pizza);
        return ResponseEntity.ok(pizzAggiorna);
    }

    @GetMapping
    public List<Pizza> getPizzaList(Pizza pizza){
        return pizzaService.viewListPizza(pizza);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pizza> viewPizzaId(@PathVariable Long id){
        Pizza pizzaId = pizzaService.getPizzaById(id);
        return ResponseEntity.ok(pizzaId);
    }


    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteByIdPizza(@PathVariable Long id){
        Pizza pizzaIdRemove = pizzaService.deletePizza(id);
        return ResponseEntity.ok("Pizza rimossa con successo");
    }

    /*se la pizza non è presente nel database non posso aggiungere nessun ingrediente
    quindi  gestisco  l'eccezione
    Anche se la pizza  è presente nel db ma l'ingrediente è null gestisco l'eccezione*/
    @PostMapping("{pizzaId}/ingredienti/{ingredienteId}")
    public ResponseEntity<Pizza> addIngredientPizza(@PathVariable Long pizzaId, @PathVariable Long ingredienteId){
        Pizza pizzaAggiungi = pizzaService.addIngredientsToPizza(pizzaId,ingredienteId);
        return ResponseEntity.ok(pizzaAggiungi);
        
        }
    }
