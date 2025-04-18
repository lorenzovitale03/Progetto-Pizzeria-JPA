package com.example.PizzeriaJPA.pizzeriaJPA.controller;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Clienti;
import com.example.PizzeriaJPA.pizzeriaJPA.services.ClientiService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clienti")
public class ClientiController {

    @Autowired ClientiService clientiService;

    @PostMapping
    public Clienti creaUtente(@RequestBody Clienti clienti){
        return clientiService.salvaUtente(clienti);
    }

    @GetMapping
    public List<Clienti> visualizzaListaUtenti(){
        return clientiService.visualizzaUtenti();
    }

    @GetMapping("{id}")
    private ResponseEntity<Clienti> visualizzaUtenteById(@PathVariable Long id){
        Clienti cliente = clientiService.visualizzaIdUtente(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> eliminaUtente(@PathVariable Long id){
        clientiService.eliminaUtenteById(id);
        return ResponseEntity.ok("Utente rimosso con successo");
    }
}
