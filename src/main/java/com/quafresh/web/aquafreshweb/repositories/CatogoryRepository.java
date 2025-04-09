package com.quafresh.web.aquafreshweb.repositories;

import com.quafresh.web.aquafreshweb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatogoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(Integer id);

    @Query("SELECT c from Category c where c.name like %:name%")
    List<Category> findByName(@Param("name") String name);
}
