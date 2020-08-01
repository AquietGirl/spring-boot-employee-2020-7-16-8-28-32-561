package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.dto.ResponseEmployee;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void should_return_2_when_find_emolyees_by_page_given_page1_and_size2_and_employees_have_3() {
        //given
        List<Employee> employees = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 2);
        for (int i = 1; i <= 3; i++) {
            employees.add(new Employee(i));
        }
        when(employeeRepository.findAll()).thenReturn(employees);

        //when
        List<Employee> result = employeeService.findEmolyeesByPage(pageable);

        //then
        assertEquals(2, result.size());
    }

    @Test
    void should_true_when_request_employee_change_to_employee_given_requestEmployee(){
        //given
        int companyId = 1;
        String name = "Alisa";
        String gender = "female";
        int age = 18;
        RequestEmployee requestEmployee = new RequestEmployee();
        requestEmployee.setCompanyId(companyId);
        requestEmployee.setName(name);
        requestEmployee.setGender(gender);
        requestEmployee.setAge(age);
        Company company = new Company();
        company.setCompanyId(companyId);
        Employee employee = new Employee();
        employee.setCompany(company);
        employee.setAge(age);
        employee.setGender(gender);
        employee.setName(name);

        when(companyRepository.findById(anyInt())).thenReturn(of(company));
        when(employeeRepository.save(any())).thenReturn(employee);

        //when
        Employee newEmployee = employeeService.addEmployee2(requestEmployee);

        //then
        assertNotNull(newEmployee);
    }

    @Test
    void should_return_true_when_employee_change_to_response_employee_given_employee(){
        //given
        String name = "Yancy";
        int id = 1;
        String gender = "male";
        int age = 18;
        String companyName = "oocl";
        Company company = new Company();
        company.setName(companyName);
        Employee employee = new Employee();
        employee.setName(name);
        employee.setId(id);
        employee.setGender(gender);
        employee.setAge(age);
        employee.setCompany(company);

        ResponseEmployee correctResonseEmployee = new ResponseEmployee();
        correctResonseEmployee.setAge(age);
        correctResonseEmployee.setGender(gender);
        correctResonseEmployee.setEmployeeId(id);
        correctResonseEmployee.setCompanyName(company.getName());
        correctResonseEmployee.setName(name);


        //when
      ResponseEmployee responseEmployee = ResponseEmployee.to(employee);

        //then
        assertTrue(correctResonseEmployee.equals(responseEmployee));
    }


}