package com.quafresh.web.aquafreshweb.service.guess;

import com.quafresh.web.aquafreshweb.entity.Technology;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TechnologyService {
    public ResponseEntity<List<Technology>> getAllTechnology();
}
