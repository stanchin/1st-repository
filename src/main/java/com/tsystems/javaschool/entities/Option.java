package com.tsystems.javaschool.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "option_name")
    private String name;
    @Column(name = "option_price")
    private BigDecimal optionPrice;
    @Column(name = "connection_price")
    private BigDecimal connectionPrice;
    @ManyToMany
    @JoinTable(name = "options_incompatible",
                joinColumns = @JoinColumn(name = "inc_option_id"),
                inverseJoinColumns = @JoinColumn(name = "base_option_id"))
    private List<Option> incOptions;
    @ManyToMany
    @JoinTable(name = "options_required",
                joinColumns = @JoinColumn(name = "req_option_id"),
                inverseJoinColumns = @JoinColumn(name = "base_option_id"))
    private List<Option> reqOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long optionId) {
        this.id = optionId;
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

    public List<Option> getReqOptions() {
        return reqOptions;
    }

    public void setReqOptions(List<Option> required) {
        this.reqOptions = required;
    }

    public List<Option> getIncOptions() {
        return incOptions;
    }

    public void setIncOptions(List<Option> incompatible) {
        this.incOptions = incompatible;
    }
}
