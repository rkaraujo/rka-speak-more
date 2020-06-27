package com.telzir.falemais.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
@Data
public class PhoneCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "source_ddd")
    private Integer sourceDDD;

    @Column(name = "destination_ddd")
    private Integer destinationDDD;

    @Column(name = "cost_per_minute")
    private BigDecimal costPerMinute;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public BigDecimal calculateTotalCost(double minutes) {
        return costPerMinute.multiply(BigDecimal.valueOf(minutes))
                .setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal calculateTotalCostExtraFee(double minutes, double extraFeePercentage) {
        return costPerMinute.multiply(BigDecimal.valueOf(minutes))
                .multiply(BigDecimal.valueOf(1 + extraFeePercentage))
                .setScale(2, RoundingMode.DOWN);
    }

}
