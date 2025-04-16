package com.example.PizzeriaJPA.pizzeriaJPA.controller;

import com.example.PizzeriaJPA.pizzeriaJPA.dto.OrderDTO;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Clienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Ordini;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.services.ClientiService;
import com.example.PizzeriaJPA.pizzeriaJPA.services.OrdiniService;
import com.example.PizzeriaJPA.pizzeriaJPA.services.PizzaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordini")
public class OrdiniController {

    @Autowired OrdiniService ordiniService;
    @Autowired ClientiService clientiService;
    @Autowired PizzaService pizzaService;

    //eseguo la creazione dell'ordine associato all'id  dell'utente
    //in ogni caso devo gestire l'eccezione: Se non esiste l'id dell'utente
    //o l'id della pizza o entrambi, non posso creare l'ordine
    @PostMapping("{clientId}/ordina/{pizzaId}")
    public ResponseEntity<Ordini> creaOrdine(@RequestBody OrderDTO OrderDTO, @PathVariable Long clientId, @PathVariable Long pizzaId){
        //mi recupero l'id  del cliente
        Clienti cliente = clientiService.visualizzaIdUtente(clientId);
        //mi recupero l'id della pizza
        Pizza pizza = pizzaService.getPizzaById(pizzaId);

        if(pizza != null && cliente == null || pizza == null && cliente == null){
            throw new EntityNotFoundException("Impossibile completare l'ordine, utente e/o pizza non presenti nel sistema");
        }else{
             Ordini ordine = ordiniService.ordinaPizza(OrderDTO);
             return ResponseEntity.status(HttpStatus.CREATED).body(ordine);
        }
    }
}
