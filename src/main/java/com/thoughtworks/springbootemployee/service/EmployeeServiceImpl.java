package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.dto.ResponseEmployee;
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
    public ResponseEmployee findEmployeeById(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(NotFoundException::new);
        return ResponseEmployee.to(employee);
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
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(int employeeId, RequestEmployee requestEmployee) {
        Employee employee = RequestEmployee.to(requestEmployee);
        Company company = companyRepository.findById(requestEmployee.getCompanyId()).orElseThrow(NotFoundException::new);
        employee.setId(employeeId);
        employee.setCompany(company);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public Employee addEmployee2(RequestEmployee requestEmployee) {
        Employee employee = RequestEmployee.to(requestEmployee);
        Company company = companyRepository.findById(requestEmployee.getCompanyId()).orElseThrow(NotFoundException::new);
        employee.setCompany(company);
        return employeeRepository.save(employee);
    }
}
