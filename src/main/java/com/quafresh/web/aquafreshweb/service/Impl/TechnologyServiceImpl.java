package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.entity.Technology;
import com.quafresh.web.aquafreshweb.repositories.TechnologyRepository;
import com.quafresh.web.aquafreshweb.service.guess.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TechnologyServiceImpl implements TechnologyService {
    @Autowired
    TechnologyRepository technologyRepository;
    @Override
    public ResponseEntity<List<Technology>> getAllTechnology() {
        try {
            return new ResponseEntity<>(technologyRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    }

