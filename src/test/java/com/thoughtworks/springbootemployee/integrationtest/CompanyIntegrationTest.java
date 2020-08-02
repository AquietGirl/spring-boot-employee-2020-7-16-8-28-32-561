package com.thoughtworks.springbootemployee.integrationtest;

import com.jayway.jsonpath.JsonPath;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

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
    void should_return_company_when_find_company_by_id_given_company_id() throws Exception {
        Company company = new Company();
        int companyId = companyRepository.save(company).getCompanyId();

        mockMvc.perform(get("/companies/" + companyId)).andExpect(status().isOk()).andExpect(jsonPath("companyId").value(companyId));
    }

    @Test
    void should_return_company_when_find_company_by_page_given_page1_and_size1() throws  Exception{
        Company company1 = new Company();
        Company company2 = new Company();
        companyRepository.save(company1);
        int companyId2 = companyRepository.save(company2).getCompanyId();
        mockMvc.perform(get("/companies")
                .param("page" , "1")
                .param("size" , "1")
                .param("isSelectAll","false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("*" , hasSize(1)))
                .andExpect(jsonPath("[0].companyId").value(companyId2));
    }

    @Test
    void should_return_company_when_find_company_by_page() throws  Exception{
        Company company1 = new Company();
        Company company2 = new Company();
        companyRepository.save(company1);
        int companyId2 = companyRepository.save(company2).getCompanyId();
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("*" , hasSize(2)));
    }
}
