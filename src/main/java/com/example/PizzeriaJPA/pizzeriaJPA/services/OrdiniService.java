package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.dto.OrderDTO;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Clienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Ordini;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.models.StatoOrdine;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.ClientiRepository;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.OrdiniRepository;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdiniService {

    @Autowired OrdiniRepository ordiniRepository;
    @Autowired PizzaRepository pizzaRepository;
    @Autowired ClientiRepository clientiRepository;

    public Ordini ordinaPizza(OrderDTO OrderDTO){
        //recupero l'id del cliente
        Clienti clienti = clientiRepository.findById(OrderDTO.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Pizza e/o ordine non trovato"));
        //recupero la lista di pizze dal loro id
        List<Pizza> pizzaList = pizzaRepository.findAllById(OrderDTO.getPizzaIdList());

        //vado a crearmi l'ordine

        Ordini ordineCrea = new Ordini();
        ordineCrea.setData(LocalDate.now());
        ordineCrea.setCliente(clienti);
        ordineCrea.setPizzeOrdinate(pizzaList);
        ordineCrea.setStato(StatoOrdine.PREPARAZIONE);

        //salvo l'ordine nel db
        return ordiniRepository.save(ordineCrea);
    }

    public Ordini visualizzaIdOrdine(Long id){
        return ordiniRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Ordine non trovato"));
    }

    public Ordini annullaOrdineDaId(Long id){
        //recupero l'ordine da annullare
        Ordini ordineAnnulla = ordiniRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ordine non trovato, impossibile annullare"));

        //annullo l'ordine
        ordineAnnulla.setData(LocalDate.now());
        ordineAnnulla.setStato(StatoOrdine.ANNULLATO);

        //salvo nel db l'ordine
        return ordiniRepository.save(ordineAnnulla);
    }

    public Ordini ordineConsegnatoDaId(Long idOrder, Long idClient){
        //recupero l'ordine da id
        Ordini consegna = ordiniRepository.findById(idOrder).orElse(null);
        //recupero il cliente da id
        Clienti cliente = clientiRepository.findById(idClient).orElse(null);

        if(consegna != null && cliente != null){
            consegna.setData(LocalDate.now());
            consegna.setStato(StatoOrdine.CONSEGNATO);
            consegna.setCliente(cliente);
            return ordiniRepository.save(consegna);
        }else{
            throw new EntityNotFoundException("Ordine non trovato");
        }
    }

    public List<Ordini> visualizzaTuttiOrdini(){
        return ordiniRepository.findAll();
    }
}
