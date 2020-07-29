package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    @Mock
    private EmployeeRepository employeeRepository;

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


}