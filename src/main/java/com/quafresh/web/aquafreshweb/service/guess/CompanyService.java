package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.entity.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    public ResponseEntity<List<Company>> getAllCompany();
}
