package com.tsystems.javaschool.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer basketId;
    private Tariff tariff;
    private List<Option> options;
    private Boolean block;
}
