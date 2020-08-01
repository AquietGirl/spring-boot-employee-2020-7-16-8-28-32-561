package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

        Company findCompanyById(int companyId);
        List<Company> findCompaniesByPage(Pageable pageable);
        List<Employee> findEmployeesByCompanyId(int companyId);
        Company addCompany(Company company);
        Company updateCompany(int companyId, Company company);
        void deleteCompany(int companyId);
}
