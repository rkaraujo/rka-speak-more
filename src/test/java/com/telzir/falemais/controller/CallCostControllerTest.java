package com.telzir.falemais.controller;

import com.telzir.falemais.dto.CallCostDTO;
import com.telzir.falemais.exception.PhoneCostNotFoundException;
import com.telzir.falemais.exception.PhonePlanNotFoundException;
import com.telzir.falemais.service.CallCostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CallCostControllerTest {

    @Mock
    private CallCostService callCostService;

    @InjectMocks
    private CallCostController callCostController;

    @Test
    void testCalculateCost() {
        // given
        Integer planId = 1;
        Integer sourceDDD = 11;
        Integer destinationDDD = 16;
        Double time = 20.0;

        BigDecimal expectedCostWithPlan = BigDecimal.ZERO;
        BigDecimal expectedCosthWithoutPlan = new BigDecimal("38.00");
        CallCostDTO cost = new CallCostDTO(expectedCostWithPlan, expectedCosthWithoutPlan);
        when(callCostService.calculateCost(planId, sourceDDD, destinationDDD, time)).thenReturn(cost);

        // when
        CallCostDTO result = callCostController.calculateCost(planId, sourceDDD, destinationDDD, time);

        // then
        assertThat(result.getCostWithPlan(), is(expectedCostWithPlan));
        assertThat(result.getCostWithoutPlan(), is(expectedCosthWithoutPlan));
    }

    @Test
    void testCalculateCost_planNotFound() {
        // given
        Integer planId = 1;
        Integer sourceDDD = 18;
        Integer destinationDDD = 17;
        Double time = 100.0;

        when(callCostService.calculateCost(planId, sourceDDD, destinationDDD, time)).thenThrow(new PhonePlanNotFoundException());

        // then
        assertThrows(PhonePlanNotFoundException.class,
                () -> callCostController.calculateCost(planId, sourceDDD, destinationDDD, time));

    }

    @Test
    void testCalculateCost_costNotFound() {
        // given
        Integer planId = 1;
        Integer sourceDDD = 18;
        Integer destinationDDD = 17;
        Double time = 100.0;

        when(callCostService.calculateCost(planId, sourceDDD, destinationDDD, time)).thenThrow(new PhoneCostNotFoundException());

        // then
        assertThrows(PhoneCostNotFoundException.class,
                () -> callCostController.calculateCost(planId, sourceDDD, destinationDDD, time));

    }

}
