package com.thoughtworks.springbootemployee.service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.AddWrongException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import javassist.compiler.NoFieldException;
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

        return companyRepository.findById(companyId).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Company> findCompaniesByPage(Pageable pageable) {
        return companyRepository.findAll(pageable).getContent();
    }

    @Override
    public Company addCompany(Company company) {
        Company companyResult = companyRepository.save(company);
        return companyResult;
    }

    // TODO
    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
}

    @Override
    public List<Employee> findEmployeesByCompanyId(int companyId){
        return companyRepository.findById(companyId).orElseThrow(NotFoundException::new).getEmployees();
    }

    @Override
    public void deleteCompany(int companyId) {
         companyRepository.deleteById(companyId);
    }
}
