package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
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
    public Employee addEmployee(RequestEmployee requestEmployee) {
        Employee employee = RequestEmployee.to(requestEmployee);
        Company company = companyRepository.findById(requestEmployee.getCompanyId()).orElseThrow(NotFoundException::new);
        employee.setCompany(company);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee addEmployee2(RequestEmployee requestEmployee) {
        Employee employee = RequestEmployee.to(requestEmployee);
        Company company = companyRepository.findById(requestEmployee.getCompanyId()).orElseThrow(NotFoundException::new);
        employee.setCompany(company);
        return employeeRepository.save(employee);
    }
}
