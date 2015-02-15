package com.tsystems.javaschool.entities;

import javax.persistence.*;

@Entity
public class Admin {

    @Id
    private Integer adminId;
    private String adminPass;

}
