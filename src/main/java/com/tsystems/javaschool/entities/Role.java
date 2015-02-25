package com.tsystems.javaschool.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long roleId) {
        this.id = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
