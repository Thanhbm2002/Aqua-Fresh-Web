package com.quafresh.web.aquafreshweb.repositories;

import com.quafresh.web.aquafreshweb.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Optional<Color> findById(Integer id);

    @Query("SELECT c from Color c where c.name like %:name%")
    List<Color> findByName(@Param("name") String name);
}
