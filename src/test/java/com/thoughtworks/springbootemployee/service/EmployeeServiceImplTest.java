package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    void should_return_name_when_find_employee_by_id_given_correct_employee_id() {
        //given
        int employeeId = 1;
        String emplyeeName = "Yancy";
        Employee employee = new Employee(employeeId);
        employee.setName(emplyeeName);
        Company company = new Company();
        employee.setCompany(company);

        when(employeeRepository.findById(employeeId)).thenReturn(of(employee));

        //when
        String result = employeeService.findEmployeeById(employeeId).getName();

        //then
        Assertions.assertEquals(emplyeeName, result);
    }

    @Test
    void should_return_exception_when_find_employee_by_id_given_incorrect_employee_id() {
        //given
        int employeeId = 1;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        //when
        Throwable notFoundException = assertThrows(NotFoundException.class, () -> employeeService.findEmployeeById(employeeId));

        //then
        assertEquals("Can not find employee by id.", notFoundException.getMessage());
    }

    @Test
    void should_return_employee_size_when_find_employee_by_gender_given_employee_gender() {
        //given
        Company company = new Company();
        Employee employee = new Employee();
        employee.setGender("male");
        employee.setCompany(company);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepository.findByGender(anyString())).thenReturn(employees);

        //when
        int result = employeeService.findEmployeesByGender("male").size();

        //then
        assertEquals(1, result);
    }

    @Test
    void should_return_exception_when_find_employee_by_gender_given_employee_gender() {
        //given
        when(employeeRepository.findByGender(anyString())).thenReturn(new ArrayList<>());

        //when
        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> employeeService.findEmployeesByGender("male"));

        //then
        assertEquals("Can not find employee by gender.", notFoundException.getMessage());
    }

    @Test
    void should_return_employees_size_when_find_employees_by_page_given_page_1_and_size_2() {
        //given
        Company company = new Company();
        List<Employee> employees = new ArrayList<>();
        for(int i = 1; i <= 2 ; i ++){
            Employee employee = new Employee(i);
            employee.setCompany(company);
            employees.add(employee);
        }
        Pageable pageable = PageRequest.of(1,2);
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(employees));

        //when
        int result = employeeService.findEmolyeesByPage(pageable).size();

        //then
        assertEquals(2, result);
    }

    @Test
    void should_return_exception_when_find_employees_by_page_given_page1_size2(){
        //given
        Pageable pageable = PageRequest.of(1 , 2);
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        //when
        NotFoundException notFoundException = assertThrows(NotFoundException.class , () -> employeeService.findEmolyeesByPage(pageable));

        //then
        assertEquals("Can not find employee by page", notFoundException.getMessage());
    }

    @Test
    void should_return_times_when_add_employee_given_employee() {
        //given
        RequestEmployee requestEmployee = new RequestEmployee();
        requestEmployee.setCompanyId(1);
        when(employeeRepository.save(any())).thenReturn(new Employee());
        when(companyRepository.findById(anyInt())).thenReturn(of(new Company()));

        //when
        employeeService.addEmployee(requestEmployee);

        //then
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
}