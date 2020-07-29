package com.thoughtworks.springbootemployee.service;

import com.oracle.jrockit.jfr.ValueDefinition;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.AddWrongException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    @Override
    public List<Employee> findEmolyeesByPage(Pageable pageable) {
       return employeeRepository.findAll(pageable).getContent();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        Employee employeeResult =  employeeRepository.save(employee);
        if (employeeResult == null) {
            new AddWrongException();
        }
        return employeeResult;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
