package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.entity.Category;
import com.quafresh.web.aquafreshweb.repositories.CatogoryRepository;
import com.quafresh.web.aquafreshweb.service.guess.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CatogoryRepository catogoryRepository;
    @Override
    public ResponseEntity<List<Category>> getAllCategory() {
        try {
            return new ResponseEntity<>(catogoryRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
