package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping("{companyId}")
    public Company updateCompany(@PathVariable  int companyId ,@RequestBody Company company) {
        return companyService.updateCompany(companyId, company);
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
