package com.tsystems.javaschool.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Basket {

    @Id
    private Integer basketId;
    private Tariff tariff;
    private Set<Option> options;
    private Boolean block;
}
