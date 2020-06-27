package com.telzir.falemais.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CallCostDTO {

    private final BigDecimal costWithPlan;
    private final BigDecimal costWithoutPlan;

}
