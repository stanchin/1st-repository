package com.tsystems.javaschool.entities;

import javax.persistence.*;

@Entity
@Table(name = "tel_numbers")
public class Number {

    @Id
    private Long number;
    @OneToOne(mappedBy = "number")
    private Contract contract;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
