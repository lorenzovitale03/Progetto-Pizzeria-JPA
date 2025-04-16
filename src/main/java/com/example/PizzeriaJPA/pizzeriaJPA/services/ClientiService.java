package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Clienti;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.ClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//Registrazione , visualizzazione e rimozione dei clienti.
public class ClientiService {

    @Autowired ClientiRepository clientiRepository;

    public Clienti salvaUtente(Clienti clienti){
        return clientiRepository.save(clienti);
    }

    public List<Clienti> visualizzaUtenti(){
        return clientiRepository.findAll();
    }

    public void eliminaUtenteById(Long id){
        clientiRepository.deleteById(id);
    }

    public Clienti visualizzaIdUtente(Long id){
        return clientiRepository.findById(id).orElse(null);
    }
}
