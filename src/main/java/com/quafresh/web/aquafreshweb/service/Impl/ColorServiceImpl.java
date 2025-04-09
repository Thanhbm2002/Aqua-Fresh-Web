package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.entity.Color;
import com.quafresh.web.aquafreshweb.repositories.ColorRepository;
import com.quafresh.web.aquafreshweb.service.guess.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepository colorRepository;
    @Override
    public ResponseEntity<List<Color>> getAllColor() {
        try {
            return new ResponseEntity<>(colorRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
    }

