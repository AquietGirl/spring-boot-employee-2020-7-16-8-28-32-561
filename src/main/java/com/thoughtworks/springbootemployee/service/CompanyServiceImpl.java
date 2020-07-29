package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
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
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(int id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public List<Company> findCompaniesByPage(Pageable pageable) {
        return companyRepository.findAll(pageable).getContent();
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }
}
