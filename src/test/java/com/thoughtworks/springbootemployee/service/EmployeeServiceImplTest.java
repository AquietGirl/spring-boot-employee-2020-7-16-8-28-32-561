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

import javax.xml.ws.Response;

import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void should_return_name_when_find_employee_by_id_given_correct_employee_id() {
        //given
        int employeeId =1;
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
    void should_return_exception_when_find_employee_by_id_given_incorrect_employee_id (){
        //given
        int employeeId = 1;
        Employee employee = new Employee(employeeId);
        Company company = new Company();
        employee.setCompany(company);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        //when
        Throwable notFoundException = assertThrows(NotFoundException.class , () -> employeeService.findEmployeeById(employeeId));

        //then
        assertEquals("Can not find employee by id." , notFoundException.getMessage());
    }


}