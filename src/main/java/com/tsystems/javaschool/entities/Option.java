package com.tsystems.javaschool.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer optionId;
    @Column(name = "option_name")
    private String name;
    @Column(name = "option_price")
    private BigDecimal optionPrice;
    @Column(name = "connection_price")
    private BigDecimal connectionPrice;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(BigDecimal optionPrice) {
        this.optionPrice = optionPrice;
    }

    public BigDecimal getConnectionPrice() {
        return connectionPrice;
    }

    public void setConnectionPrice(BigDecimal connectionPrice) {
        this.connectionPrice = connectionPrice;
    }
}
