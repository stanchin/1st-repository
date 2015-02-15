package com.tsystems.javaschool.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    @Column(name = "tariff_id")
    private Integer tariffId;
    @Column(name = "tariff_name")
    private String name;
    @Column(name = "tariff_price")
    private BigDecimal price;
    @ManyToMany
    @JoinTable(name = "tariffs_options",
                joinColumns = @JoinColumn(name = "option_id"),
                inverseJoinColumns = @JoinColumn(name = "tariff_id"))
    private Set<Option> options;

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
