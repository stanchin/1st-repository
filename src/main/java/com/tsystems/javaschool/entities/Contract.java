package com.tsystems.javaschool.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @Column(name = "contract_id")
    private Integer contractId;
    @Column(name = "number")
    private Integer number;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private Boolean blocked;
    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    private Set<Option> options;

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

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
