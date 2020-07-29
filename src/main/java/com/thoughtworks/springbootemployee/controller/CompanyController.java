package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
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


    @GetMapping("{id}")
    public Company findCompanyById(@PathVariable int id) {
        return companyService.findCompanyById(id);
    }

    @GetMapping
    public List<Company> findCompaniesByPage(@PageableDefault(size = 2) Pageable pageable, @RequestParam(defaultValue = "true") boolean isSelectAll) {
        if (isSelectAll) return companyService.findCompaniesByPage(pageable);
        return companyService.findAllCompanies();
    }

    @PostMapping
    public Company addCompany(Company company){
        return companyService.addCompany(company);
    }
}
