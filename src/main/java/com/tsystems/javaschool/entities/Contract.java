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
    @Column(name = "block_by_user")
    private boolean blockedByUser;
    @Column(name = "block_by_operator")
    private boolean blockedByOperator;
    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;
    @ManyToMany
    @JoinTable(name = "contract_options",
                joinColumns = @JoinColumn(name = "option_id"),
                inverseJoinColumns = @JoinColumn(name = "contract_id"))
    private List<Option> options;

    public boolean getBlockedByOperator() {
        return blockedByOperator;
    }

    public void setBlockedByOperator(boolean blockedByOperator) {
        this.blockedByOperator = blockedByOperator;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

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

    public boolean getBlockedByUser() {
        return blockedByUser;
    }

    public void setBlockedByUser(boolean blocked) {
        this.blockedByUser = blocked;
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
