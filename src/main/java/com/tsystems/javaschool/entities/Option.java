package com.tsystems.javaschool.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
/*
* The option class that describes any option of Mobile Operator System. Linked with
* database, using JPA.
* */
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
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Option> incOptions;
    @ManyToMany
    @JoinTable(name = "options_required",
                joinColumns = @JoinColumn(name = "req_option_id"),
                inverseJoinColumns = @JoinColumn(name = "base_option_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;

        Option option = (Option) o;

        if (!connectionPrice.equals(option.connectionPrice)) return false;
        if (!name.equals(option.name)) return false;
        if (!optionPrice.equals(option.optionPrice)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + optionPrice.hashCode();
        result = 31 * result + connectionPrice.hashCode();
        return result;
    }
}
