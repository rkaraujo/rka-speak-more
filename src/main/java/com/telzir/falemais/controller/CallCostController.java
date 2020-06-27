package com.telzir.falemais.controller;

import com.telzir.falemais.dto.CallCostDTO;
import com.telzir.falemais.service.CallCostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallCostController {

    private final CallCostService callCostService;

    public CallCostController(CallCostService callCostService) {
        this.callCostService = callCostService;
    }

    @GetMapping("/v1/phone-call/cost")
    public CallCostDTO calculateCost(@RequestParam Integer planId,
                                     @RequestParam Integer sourceDDD,
                                     @RequestParam Integer destinationDDD,
                                     @RequestParam Double time) {
        return callCostService.calculateCost(planId, sourceDDD, destinationDDD, time);
    }
}
