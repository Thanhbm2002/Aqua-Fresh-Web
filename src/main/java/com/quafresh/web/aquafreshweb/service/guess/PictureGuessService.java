package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.entity.Picture;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PictureGuessService {
    public ResponseEntity<List<Picture>> getAllPicture();
}
