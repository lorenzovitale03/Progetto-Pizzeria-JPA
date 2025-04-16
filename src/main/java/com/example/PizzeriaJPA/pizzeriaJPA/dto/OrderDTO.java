package com.example.PizzeriaJPA.pizzeriaJPA.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {

    private Long clienteId;
    private List<Long> pizzaIdList;
}
