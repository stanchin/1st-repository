package com.tsystems.javaschool.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "tariff_name")
    private String name;
    @Column(name = "tariff_price")
    private BigDecimal price;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tariffs_options",
                joinColumns = @JoinColumn(name = "tariff_id"),
                inverseJoinColumns = @JoinColumn(name = "option_id"))
    private List<Option> options;

    public Long getId() {
        return id;
    }

    public void setId(Long tariffId) {
        this.id = tariffId;
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
