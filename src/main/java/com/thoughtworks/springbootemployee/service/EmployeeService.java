package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee findEmployeeById(int id);
    List<Employee> findEmployeesByGender(String gender);
    List<Employee> findEmolyeesByPage(Pageable pageable);
    Employee addEmployee(Employee employee);

}
