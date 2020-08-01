package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl  implements  CompanyService{

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company findCompanyById(int companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Can not find company by id."));
    }

    @Override
    public List<Company> findCompaniesByPage(Pageable pageable) {
        List<Company> companies = companyRepository.findAll(pageable).getContent();
        if(companies.isEmpty()){
            throw  new NotFoundException("can't not find company by page");
        }
        return companies;
    }

    @Override
    public Company addCompany(Company company) {
        Company companyResult = companyRepository.save(company);
        return companyResult;
    }

    @Override
    public Company updateCompany(int companyId, Company company) {
        company.setCompanyId(companyId);
        return companyRepository.save(company);
}

    @Override
    public List<Employee> findEmployeesByCompanyId(int companyId){
        return companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Can not find company by id.")).getEmployees();
    }

    @Override
    public void deleteCompany(int companyId) {
         companyRepository.deleteById(companyId);
    }
}
