package com.example.PizzeriaJPA.pizzeriaJPA.services;

import com.example.PizzeriaJPA.pizzeriaJPA.dto.OrderDTO;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Clienti;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Ordini;
import com.example.PizzeriaJPA.pizzeriaJPA.models.Pizza;
import com.example.PizzeriaJPA.pizzeriaJPA.models.StatoOrdine;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.ClientiRepository;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.OrdiniRepository;
import com.example.PizzeriaJPA.pizzeriaJPA.repositories.PizzaRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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
        Clienti clienti = clientiRepository.findById(OrderDTO.getClienteId()).orElse(null);
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
        return ordiniRepository.findById(id).orElse(null);
    }

    public Ordini annullaOrdineDaId(OrderDTO orderDTO, Long id){
        //recupero l'id del cliente che vuole eliminare l'ordine
        Clienti clienti = clientiRepository.findById(orderDTO.getClienteId()).orElse(null);
        //recupero l'ordine da annullare
        Ordini ordineAnnulla = ordiniRepository.findById(id).orElse(null);

        //annullo l'ordine
        ordineAnnulla.setData(LocalDate.now());
        ordineAnnulla.setStato(StatoOrdine.ANNULLATO);
        ordineAnnulla.setCliente(clienti);

        //salvo nel db l'ordine
        return ordiniRepository.save(ordineAnnulla);
    }
}
