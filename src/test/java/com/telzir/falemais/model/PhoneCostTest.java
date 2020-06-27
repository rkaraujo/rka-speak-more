package com.telzir.falemais.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PhoneCostTest {

    @Test
    void testCalculateTotalCost() {
        PhoneCost phoneCost = new PhoneCost();
        phoneCost.setCostPerMinute(new BigDecimal("1.00"));

        assertThat(phoneCost.calculateTotalCost(20), is(new BigDecimal("20.00")));
    }

    @Test
    void testCalculateTotalCostExtraFee() {
        PhoneCost phoneCost = new PhoneCost();
        phoneCost.setCostPerMinute(new BigDecimal("1.00"));

        assertThat(phoneCost.calculateTotalCostExtraFee(20, 0.1), is(new BigDecimal("22.00")));
    }
}