package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.entity.Color;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ColorService {
    public ResponseEntity<List<Color>> getAllColor();
}
