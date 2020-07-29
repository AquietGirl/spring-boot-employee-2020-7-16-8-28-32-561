package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    @Override
    public List<Employee> findEmolyeesByPage(int page, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(page , pageSize)).getContent();
    }


}
