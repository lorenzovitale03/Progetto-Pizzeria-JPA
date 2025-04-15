package com.example.PizzeriaJPA.pizzeriaJPA.controller;

import com.example.PizzeriaJPA.pizzeriaJPA.exception.MyExceptionHandler;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
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

    @PostMapping
    public Pizza getPizza(@RequestBody Pizza pizza){
        return pizzaService.savePizza(pizza);
    }

    //se la pizza non è presente nel db, gestisci l'eccezione "Pizza non trovata",
    //non posso in quel caso aggiornare la pizza
    @PutMapping("{id}")
    public ResponseEntity<Pizza> aggiornoPizza(@PathVariable Long id, @RequestBody Pizza pizza){
        //prima recupero la pizza dal suo id
        Pizza pizzaRecupera = pizzaService.getPizzaById(id);
        //verifico se è presente o no nel db
        if(pizzaRecupera != null){
            Pizza recuperato = pizzaService.updatePizza(id,pizza);
            return ResponseEntity.ok(recuperato);
        }else {
            throw new EntityNotFoundException("Pizza non trovata");
        }
    }

    @GetMapping
    public List<Pizza> getPizzaList(Pizza pizza){
        return pizzaService.viewListPizza(pizza);
    }

    //se la pizza non è presente nel db, di conseguenza l'id non è esistente
    //quindi gestisci l'eccezione "Id non esistente"
    @GetMapping("{id}")
    public ResponseEntity<Pizza> viewPizzaId(@PathVariable Long id){
        Pizza pizza = pizzaService.getPizzaById(id);
        if(pizza != null){
            return ResponseEntity.ok(pizza);
        }else {
            throw new EntityExistsException("Pizza non esistente");
        }
    }

    //se la pizza non è presente nel db, di conseguenza l'id non è esistente
    //quindi gestisci l'eccezione "Pizza non trovata",non posso cancellarla
    @DeleteMapping("{id}/delete")
    public void deleteByIdPizza(@PathVariable Long id){
         //vado a recuperare la pizza tramite id
        Pizza recupera = pizzaService.getPizzaById(id);
        //se la pizza è presente nel db  procedo ad eliminarla
        if(recupera != null){
            recupera.setDisponibile(false);
            pizzaService.savePizza(recupera);
            pizzaService.deletePizza(id);
        }else{
            throw new EntityNotFoundException("Pizza non presente nel db");
        }

    }

    @PostMapping("{pizzaId}/ingredienti/{ingredienteId}")
    public Pizza addIngredientPizza(@PathVariable Long pizzaId, @PathVariable Long ingredienteId){
        return pizzaService.addIngredientsToPizza(pizzaId,ingredienteId);
    }
}
