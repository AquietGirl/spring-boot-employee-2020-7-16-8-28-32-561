package com.thoughtworks.springbootemployee.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;
    private String name;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public Company() {

    }


    public Company(String name) {
        this.name = name;
    }
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

