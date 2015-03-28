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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Number)) return false;

        Number number1 = (Number) o;

        if (!number.equals(number1.number)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
