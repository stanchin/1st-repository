package com.tsystems.javaschool.entities;

import javax.persistence.*;

@Entity
@Table(name = "tel_numbers")
public class Number {

    @Id
    private Integer number;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
