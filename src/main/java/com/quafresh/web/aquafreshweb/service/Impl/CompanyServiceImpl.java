package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.entity.Company;
import com.quafresh.web.aquafreshweb.repositories.CompanyRepository;
import com.quafresh.web.aquafreshweb.service.guess.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public ResponseEntity<List<Company>> getAllCompany() {
        try {
            return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    }

