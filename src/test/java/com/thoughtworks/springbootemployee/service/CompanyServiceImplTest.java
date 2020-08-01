package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
    void should_return_exception_when_find_company_by_page_given_page1_size1() {
        //given
        Pageable pageable = PageRequest.of(1,2);
        when(companyRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        //when
        NotFoundException notFoundException = assertThrows(NotFoundException.class , () -> companyService.findCompaniesByPage(pageable));

        //then
        assertEquals("can't not find company by page" , notFoundException.getMessage());

    }

    @Test
    void should_return_times_when_add_company_given_company() {
        //when
        companyService.addCompany(new Company());

        //then
        verify(companyRepository , times(1)).save(any(Company.class));
    }

    @Test
    void should_return_times_when_update_company_given_company() {
        //when
        companyService.updateCompany(1,new Company());

        //then
        verify(companyRepository , times(1)).save(any(Company.class));

    }

    @Test
    void findEmployeesByCompanyId() {
    }

    @Test
    void deleteCompany() {
    }
}