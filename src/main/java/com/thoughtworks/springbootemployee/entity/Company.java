package com.thoughtworks.springbootemployee.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer company_id;
    private String name;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public Company() {

    }
}

