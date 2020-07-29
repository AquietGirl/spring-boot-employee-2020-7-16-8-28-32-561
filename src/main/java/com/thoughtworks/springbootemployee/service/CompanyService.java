package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

        List<Company> findAllCompanies();
        Company findCompanyById(int id);
        List<Company> findCompaniesByPage(Pageable pageable);
        Company addCompany(Company company);
}
