package com.telzir.falemais.controller;

import com.telzir.falemais.dto.PhonePlanDTO;
import com.telzir.falemais.model.PhonePlan;
import com.telzir.falemais.service.PhonePlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhonePlanController {

    private final PhonePlanService phonePlanService;

    public PhonePlanController(PhonePlanService phonePlanService) {
        this.phonePlanService = phonePlanService;
    }

    @GetMapping("/v1/phone-plans")
    public List<PhonePlanDTO> getAllPlans() {
        List<PhonePlan> plans = phonePlanService.getAllPlans();
        return plans.stream().map(this::buildDTO).collect(Collectors.toList());
    }

    private PhonePlanDTO buildDTO(PhonePlan phonePlan) {
        PhonePlanDTO dto = new PhonePlanDTO();
        dto.setId(phonePlan.getId());
        dto.setName(phonePlan.getName());
        dto.setMinutes(phonePlan.getMinutes());
        return dto;
    }

}
