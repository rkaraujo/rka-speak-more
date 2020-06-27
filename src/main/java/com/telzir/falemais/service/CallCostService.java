package com.telzir.falemais.service;

import com.telzir.falemais.dto.CallCostDTO;
import com.telzir.falemais.exception.PhoneCostNotFoundException;
import com.telzir.falemais.exception.PhonePlanNotFoundException;
import com.telzir.falemais.model.PhoneCost;
import com.telzir.falemais.model.PhonePlan;
import com.telzir.falemais.repository.PhoneCostRepository;
import com.telzir.falemais.repository.PhonePlanRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CallCostService {

    // extra percentage charged for exceeding minutes outside plan
    private static final double EXCEEDING_MINUTES_FEE_PERCENTAGE = 0.1;

    private final PhonePlanRepository phonePlanRepository;

    private final PhoneCostRepository phoneCostRepository;

    public CallCostService(PhonePlanRepository phonePlanRepository, PhoneCostRepository phoneCostRepository) {
        this.phonePlanRepository = phonePlanRepository;
        this.phoneCostRepository = phoneCostRepository;
    }

    public CallCostDTO calculateCost(Integer planId, Integer sourceDDD, Integer destinationDDD, Double callTime) {
        PhonePlan phonePlan = phonePlanRepository.findById(planId)
                .orElseThrow(PhonePlanNotFoundException::new);

        PhoneCost phoneCost = phoneCostRepository.findBySourceDDDAndDestinationDDD(sourceDDD, destinationDDD)
                .orElseThrow(PhoneCostNotFoundException::new);

        BigDecimal costWithPlan = BigDecimal.ZERO;
        double minutesNotIncludedInPlan = callTime - phonePlan.getMinutes();
        if (minutesNotIncludedInPlan > 0) {
            costWithPlan = phoneCost.calculateTotalCostExtraFee(minutesNotIncludedInPlan, EXCEEDING_MINUTES_FEE_PERCENTAGE);
        }

        BigDecimal costWithoutPlan = phoneCost.calculateTotalCost(callTime);

        return new CallCostDTO(costWithPlan, costWithoutPlan);
    }

}
