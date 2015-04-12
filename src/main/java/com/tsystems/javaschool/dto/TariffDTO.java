package com.tsystems.javaschool.dto;


import java.math.BigDecimal;

public class TariffDTO {
    private Long tariffId;
    private String tariffName;
    private BigDecimal tariffPrice;

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public BigDecimal getTariffPrice() {
        return tariffPrice;
    }

    public void setTariffPrice(BigDecimal tariffPrice) {
        this.tariffPrice = tariffPrice;
    }
}
