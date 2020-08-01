package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void should_return_exception_when_find_company_by_id_given_company_id() {
        //given
        int companyId = 1;
        when(companyRepository.findById(anyInt())).thenReturn(Optional.empty());

        //when
        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> companyService.findCompanyById(companyId));

        //then
        assertEquals("Can not find company by id.", notFoundException.getMessage());
    }

    @Test
    void findCompaniesByPage() {
    }

    @Test
    void addCompany() {
    }

    @Test
    void updateCompany() {
    }

    @Test
    void findEmployeesByCompanyId() {
    }

    @Test
    void deleteCompany() {
    }
}