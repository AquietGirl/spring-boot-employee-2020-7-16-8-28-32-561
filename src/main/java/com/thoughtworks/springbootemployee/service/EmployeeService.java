package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee findEmployeeById(int id);
    List<Employee> findEmployeesByGender(String gender);
    List<Employee> findEmolyeesByPage(int page , int pageSize);
}
