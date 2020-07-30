package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employee findEmployeeById(int id);
    List<Employee> findEmployeesByGender(String gender);
    List<Employee> findEmolyeesByPage(Pageable pageable);
    Employee addEmployee(RequestEmployee requestEmployee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(int id);
}
