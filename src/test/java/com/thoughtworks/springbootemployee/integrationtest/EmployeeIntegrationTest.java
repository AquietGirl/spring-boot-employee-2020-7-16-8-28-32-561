package com.thoughtworks.springbootemployee.integrationtest;


import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    private void cleanUp() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_return_employee_when_find_employee_by_id_given_employee_id() throws Exception {
        Employee employee = new Employee();
        employee.setName("Yancy");
        Company company = new Company();
        company.setName("tw");
        companyRepository.save(company);
        employee.setCompany(company);
        int employeeId = employeeRepository.save(employee).getId();

        mockMvc.perform(get("/employees/" + employeeId)).andExpect(status().isOk()).andExpect(jsonPath("name").value("Yancy"));
    }

    @Test
    void should_return_employee_when_find_employee_by_gender_given_employee_gender() throws Exception {
        Employee employee = new Employee();
        employee.setGender("male");
        Company company = new Company();
        companyRepository.save(company);
        employee.setCompany(company);
        employeeRepository.save(employee);

        mockMvc.perform(get("/employees").param("gender", "male")).andExpect(status().isOk()).andExpect(jsonPath("[0]").exists());
    }

    @Test
    void should_return_employees_when_find_employee_by_page_given_employee_page() throws Exception {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Company company = new Company();
        companyRepository.save(company);
        employee1.setCompany(company);
        employee2.setCompany(company);
        employeeRepository.save(employee1);
        int employee2Id = employeeRepository.save(employee2).getId();

        mockMvc.perform(get("/employees").param("page", "1").param("size", "1").param("isSelectAll", "false"))
                .andExpect(status().isOk()).andExpect(jsonPath("[0].employeeId").value(employee2Id)).andExpect(jsonPath("*", hasSize(1)));
    }

    @Test
    void should_return_employees_when_find_employee_by_page_given_employee_unpage() throws Exception {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Company company = new Company();
        companyRepository.save(company);
        employee1.setCompany(company);
        employee2.setCompany(company);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk()).andExpect(jsonPath("*", hasSize(2)));
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() throws Exception {
        Company company = new Company();
        int companyId = companyRepository.save(company).getCompanyId();
        String employeeJson = "{\n" +
                "        \"name\": \"Yancy\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"age\": 15,\n" +
                "        \"companyId\" : " + companyId + "\n" +
                "    }";

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJson))
                .andExpect(status().isCreated()).andExpect(jsonPath("name").value("Yancy"));
    }

    @Test
    void should_return_employee_when_update_employee_given_employee() throws Exception {
        Company company = new Company();
        int companyId = companyRepository.save(company).getCompanyId();
        Employee employee = new Employee();
        employee.setName("Alisa");
        int employeeId = employeeRepository.save(employee).getId();
        String employeeJson = "{\n" +
                "        \"name\": \"Yancy\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"age\": 15,\n" +
                "        \"companyId\" : " + companyId + "\n" +
                "    }";

        mockMvc.perform(put("/employees/" + employeeId).contentType(MediaType.APPLICATION_JSON).content(employeeJson))
                .andExpect(status().isOk()).andExpect(jsonPath("name").value("Yancy"));
    }

    @Test
    void should_return_employee_when_delete_employee_given_employee() throws Exception {
        Company company = new Company();
        companyRepository.save(company).getCompanyId();
        Employee employee = new Employee();
        int employeeId = employeeRepository.save(employee).getId();

        mockMvc.perform(delete("/employees/" + employeeId))
                .andExpect(status().isOk());
    }
}
