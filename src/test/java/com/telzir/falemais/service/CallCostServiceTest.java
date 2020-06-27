package com.telzir.falemais.service;

import com.telzir.falemais.dto.CallCostDTO;
import com.telzir.falemais.exception.PhoneCostNotFoundException;
import com.telzir.falemais.exception.PhonePlanNotFoundException;
import com.telzir.falemais.model.PhoneCost;
import com.telzir.falemais.model.PhonePlan;
import com.telzir.falemais.repository.PhoneCostRepository;
import com.telzir.falemais.repository.PhonePlanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CallCostServiceTest {

    @Mock
    private PhonePlanRepository phonePlanRepository;

    @Mock
    private PhoneCostRepository phoneCostRepository;

    @InjectMocks
    private CallCostService callCostService;

    @ParameterizedTest
    @CsvSource({
            "11,16,20,1.90,30,0,38.00",
            "11,17,80,1.70,60,37.40,136.00",
            "18,11,200,1.90,120,167.20,380.00"
    })
    void testCalculateCost(Integer sourceDDD,
                           Integer destinationDDD,
                           Double time,
                           BigDecimal costPerMinute,
                           Double planMinutes,
                           BigDecimal expectedCostWithPlan,
                           BigDecimal expectedCostWithoutPlan) {
        // given
        Integer planId = 1;

        PhonePlan phonePlan = buildPhonePlan(planId, planMinutes);
        when(phonePlanRepository.findById(planId)).thenReturn(Optional.of(phonePlan));

        PhoneCost phoneCost = buildPhoneCost(sourceDDD, destinationDDD, costPerMinute);
        when(phoneCostRepository.findBySourceDDDAndDestinationDDD(sourceDDD, destinationDDD)).thenReturn(Optional.of(phoneCost));

        // when
        CallCostDTO result = callCostService.calculateCost(planId, sourceDDD, destinationDDD, time);

        assertThat(result.getCostWithPlan(), is(expectedCostWithPlan));
        assertThat(result.getCostWithoutPlan(), is(expectedCostWithoutPlan));
    }

    @Test
    void testCalculateCost_planNotFound() {
        // given
        Integer planId = 1;
        Integer sourceDDD = 18;
        Integer destinationDDD = 17;
        Double time = 100.0;

        when(phonePlanRepository.findById(planId)).thenReturn(Optional.empty());

        // then
        assertThrows(PhonePlanNotFoundException.class,
                () -> callCostService.calculateCost(planId, sourceDDD, destinationDDD, time));
    }

    @Test
    void testCalculateCost_costNotFound() {
        // given
        Integer planId = 1;
        Integer sourceDDD = 18;
        Integer destinationDDD = 17;
        Double time = 100.0;

        PhonePlan phonePlan = buildPhonePlan(planId, time);
        when(phonePlanRepository.findById(planId)).thenReturn(Optional.of(phonePlan));

        when(phoneCostRepository.findBySourceDDDAndDestinationDDD(sourceDDD, destinationDDD)).thenReturn(Optional.empty());

        // then
        assertThrows(PhoneCostNotFoundException.class,
                () -> callCostService.calculateCost(planId, sourceDDD, destinationDDD, time));
    }

    private PhonePlan buildPhonePlan(Integer id, double minutes) {
        PhonePlan phonePlan = new PhonePlan();
        phonePlan.setId(id);
        phonePlan.setMinutes(minutes);
        phonePlan.setName("FaleMais");
        return phonePlan;
    }

    private PhoneCost buildPhoneCost(Integer source, Integer destination, BigDecimal costPerMinute) {
        PhoneCost phoneCost = new PhoneCost();
        phoneCost.setId(1);
        phoneCost.setSourceDDD(source);
        phoneCost.setDestinationDDD(destination);
        phoneCost.setCostPerMinute(costPerMinute);
        return phoneCost;
    }
}