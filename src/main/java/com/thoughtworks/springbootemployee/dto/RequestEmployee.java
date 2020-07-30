package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.Employee;

public class RequestEmployee {
    private String name;
    private Integer age;
    private String gender;
    private Integer companyId;



    public static Employee to(RequestEmployee requestEmployee){
            Employee employee =new Employee();
            employee.setName(requestEmployee.getName());
            employee.setGender(requestEmployee.getGender());
            employee.setAge(requestEmployee.getAge());
            return employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
