package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.dto.ResponseEmployee;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Pageable;

        import java.util.List;

public interface EmployeeService {

    ResponseEmployee findEmployeeById(int employeeId);
    List<Employee> findEmployeesByGender(String gender);
    List<Employee> findEmolyeesByPage(Pageable pageable);
    Employee addEmployee(RequestEmployee requestEmployee);
    Employee updateEmployee(int employeeId, RequestEmployee requestEmployee);
    void deleteEmployee(int id);
}
