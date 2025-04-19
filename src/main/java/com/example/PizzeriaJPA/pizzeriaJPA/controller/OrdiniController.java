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

import java.util.List;

@RestController
@RequestMapping("/api/ordini")
public class OrdiniController {

    @Autowired OrdiniService ordiniService;
    @Autowired ClientiService clientiService;
    @Autowired PizzaService pizzaService;


    @PostMapping("{clientId}/ordina/{pizzaId}")
    public ResponseEntity<Ordini> creaOrdine(@RequestBody OrderDTO OrderDTO,@PathVariable Long clientId,@PathVariable Long pizzaId){
        Ordini ordini = ordiniService.ordinaPizza(OrderDTO);
        return ResponseEntity.ok(ordini);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ordini> visualizzaOrdineDaId(@PathVariable Long id){
         Ordini ordineVisualizza = ordiniService.visualizzaIdOrdine(id);
        return ResponseEntity.ok(ordineVisualizza);
    }

    @PostMapping("annulla/{orderId}")
    public ResponseEntity<Ordini> annullaOrdineCliente(@PathVariable Long orderId){
        Ordini annullaOrder = ordiniService.annullaOrdineDaId(orderId);
        return ResponseEntity.ok(annullaOrder);
    }

    @PostMapping("{orderId}/consegna/{clientId}")
    public ResponseEntity<Ordini> consegnaOrdineCliente(@PathVariable Long orderId, @PathVariable Long clientId){
        Ordini ordineConsegnato = ordiniService.ordineConsegnatoDaId(orderId,clientId);
        return ResponseEntity.ok(ordineConsegnato);
    }

    @GetMapping
    public List<Ordini> visualizzaOrdini(){
        return ordiniService.visualizzaTuttiOrdini();
    }
}
