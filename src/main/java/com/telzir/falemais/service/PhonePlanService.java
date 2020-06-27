package com.telzir.falemais.service;

import com.telzir.falemais.model.PhonePlan;
import com.telzir.falemais.repository.PhonePlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhonePlanService {

    private final PhonePlanRepository phonePlanRepository;

    public PhonePlanService(PhonePlanRepository phonePlanRepository) {
        this.phonePlanRepository = phonePlanRepository;
    }

    public List<PhonePlan> getAllPlans() {
        return phonePlanRepository.findAll();
    }

}
