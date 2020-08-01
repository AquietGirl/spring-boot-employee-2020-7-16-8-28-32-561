package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.dto.RequestEmployee;
import com.thoughtworks.springbootemployee.dto.ResponseEmployee;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeId}")
    public ResponseEmployee findEmployeeById(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @GetMapping(params = "gender")
    public List<ResponseEmployee> findEmployeesByGender(@RequestParam String gender) {
        return employeeService.findEmployeesByGender(gender);
    }

    @GetMapping
    public List<ResponseEmployee> findEmolyeesByPage(@PageableDefault(size = 2) Pageable pageable, @RequestParam(required = false, defaultValue = "true") boolean isSelectAll) {
        if (isSelectAll) {
            return employeeService.findEmolyeesByPage(Pageable.unpaged());
        }
        return employeeService.findEmolyeesByPage(pageable);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody RequestEmployee requestEmployee) {
        return employeeService.addEmployee(requestEmployee);
    }

    @PutMapping("{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody RequestEmployee requestEmployee) {
        return employeeService.updateEmployee(employeeId, requestEmployee);
    }


    @DeleteMapping("{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
