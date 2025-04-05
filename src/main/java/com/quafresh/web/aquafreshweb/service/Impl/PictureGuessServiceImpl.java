package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.entity.Picture;
import com.quafresh.web.aquafreshweb.repositories.PictureRepository;
import com.quafresh.web.aquafreshweb.service.guess.PictureGuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PictureGuessServiceImpl implements PictureGuessService {
    @Autowired
    PictureRepository pictureRepository;
    @Override
    public ResponseEntity<List<Picture>> getAllPicture() {
        try {
            return new ResponseEntity<>(pictureRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
