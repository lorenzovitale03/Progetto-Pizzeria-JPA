package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.models.Clienti;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.ClientiRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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

    public Clienti eliminaUtenteById(Long id){
        //recupero l'id del cliente
        Clienti clienteTrova = clientiRepository.findById(id).orElse(null);

        if(clienteTrova != null){
            clientiRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Id non trovato nel db");
        }
        return clienteTrova;

    }

    public Clienti visualizzaIdUtente(Long id){
        //recupero l'id  del cliente
        Clienti cliente =  clientiRepository.findById(id).orElse(null);

        if(cliente != null){
            return cliente;
        }else{
            throw new EntityNotFoundException("Cliente inesistente quindi non visualizzabile");
        }
    }
}
