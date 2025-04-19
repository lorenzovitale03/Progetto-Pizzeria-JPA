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

    /*se la pizza non è presente nel db, di conseguenza l'id non è esistente
    quindi gestisci l'eccezione "Id non esistente"*/
    @GetMapping("{id}")
    public ResponseEntity<Pizza> viewPizzaId(@PathVariable Long id){
        Pizza pizzaId = pizzaService.getPizzaById(id);
        return ResponseEntity.ok(pizzaId);
    }

    /*se la pizza non è presente nel db, di conseguenza l'id non è esistente
    quindi gestisci l'eccezione "Pizza non trovata",non posso cancellarla*/
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

    /*se la pizza non è presente nel database non posso aggiungere nessun ingrediente
    quindi  gestisco  l'eccezione
    Anche se la pizza  è presente nel db ma l'ingrediente è null gestisco l'eccezione*/
    @PostMapping("{pizzaId}/ingredienti/{ingredienteId}")
    public ResponseEntity<Pizza> addIngredientPizza(@PathVariable Long pizzaId, @PathVariable Long ingredienteId){
        //mi vado a recuperare la pizza tramite il suo id
        Pizza pizzaTrova = pizzaService.getPizzaById(pizzaId);
        //mi vado a recuperare anche l'ingrediente tramite il suo id
        Ingredienti pizzaIngrediente = ingredientiService.getIngredientById(ingredienteId);

        /*se la pizza è presente nel database ma l'ingrediente non è presente oppure sia la pizza che l'ingrediente
        non è presente nel db, gestisco l'eccezione. Altrimenti se presente nel db lo posso aggiungere alla pizza*/

        if(pizzaTrova != null && pizzaIngrediente == null || pizzaTrova == null && pizzaIngrediente == null){
            throw new EntityExistsException("Ingrediente e/o pizza inesistente");
        }else{
            pizzaService.addIngredientsToPizza(pizzaId,ingredienteId);
            return ResponseEntity.ok(pizzaTrova);
        }

    }
}
