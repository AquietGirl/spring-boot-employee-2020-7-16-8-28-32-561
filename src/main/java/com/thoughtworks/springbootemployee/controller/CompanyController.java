package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("{companyId}")
    public Company findCompanyById(@PathVariable int companyId) {
        return companyService.findCompanyById(companyId);
    }

    @GetMapping
    public List<Company> findCompaniesByPage(@PageableDefault(size = 2) Pageable pageable, @RequestParam(defaultValue = "true") boolean isSelectAll) {
        if (isSelectAll) return companyService.findCompaniesByPage(Pageable.unpaged());
        return companyService.findCompaniesByPage(pageable);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping
    public Company updateCompany(@RequestBody Company company) {
        return companyService.updateCompany(company);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> findEmployeesByCompanyId(@PathVariable  int companyId) {
        return companyService.findEmployeesByCompanyId(companyId);
    }

    @DeleteMapping("{companyId}")
    public void deleteCompany(@PathVariable  int companyId) {
        companyService.deleteCompany(companyId);
    }
}
