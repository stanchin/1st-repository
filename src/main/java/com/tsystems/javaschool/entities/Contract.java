package com.tsystems.javaschool.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
/*
* The contract class that describes any contract concluded in Mobile Operator System.
* Contract connected with database, using JPA.
* */
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "number")
    private Number number;
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
                joinColumns = @JoinColumn(name = "contract_id"),
                inverseJoinColumns = @JoinColumn(name = "option_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Option> options;

    public boolean getBlockedByOperator() {
        return blockedByOperator;
    }

    public void setBlockedByOperator(boolean blockedByOperator) {
        this.blockedByOperator = blockedByOperator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long contractId) {
        this.id = contractId;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;

        Contract contract = (Contract) o;

        if (!client.equals(contract.client)) return false;
        if (!number.equals(contract.number)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }
}
