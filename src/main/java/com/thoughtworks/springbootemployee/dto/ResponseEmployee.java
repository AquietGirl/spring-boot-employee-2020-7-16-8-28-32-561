package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.Objects;

public class ResponseEmployee {

    private Integer employeeId;
    private String name;
    private String gender;
    private Integer age;
    private String companyName;

    public static ResponseEmployee to(Employee employee){
            ResponseEmployee responseEmployee = new ResponseEmployee();
            responseEmployee.setAge(employee.getAge());
            responseEmployee.setCompanyName(employee.getCompany().getName());
            responseEmployee.setEmployeeId(employee.getId());
            responseEmployee.setGender(employee.getGender());
            responseEmployee.setName(employee.getName());
            return responseEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseEmployee that = (ResponseEmployee) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(age, that.age) &&
                Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, gender, age, companyName);
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
