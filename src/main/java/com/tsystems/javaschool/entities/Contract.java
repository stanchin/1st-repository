package com.tsystems.javaschool.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    private List<Option> options;

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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
