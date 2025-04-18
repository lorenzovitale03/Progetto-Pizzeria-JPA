package com.example.PizzeriaJPA.pizzeriaJPA.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull
    private Long clienteId;
    private List<Long> pizzaIdList;
}
