package com.tsystems.javaschool.entities;

import javax.persistence.*;

@Entity
@Table(name = "tel_numbers")
public class Number {

    @Id
    private Integer number;
    @OneToOne(mappedBy = "number")
    private Contract contract;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Contract getClient() {
        return contract;
    }

    public void setClient(Contract contract) {
        this.contract = contract;
    }
}
