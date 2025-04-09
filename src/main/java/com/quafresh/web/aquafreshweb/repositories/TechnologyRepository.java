package com.quafresh.web.aquafreshweb.repositories;

import com.quafresh.web.aquafreshweb.entity.Company;
import com.quafresh.web.aquafreshweb.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {
    Optional<Technology> findById(Integer id);

    @Query("SELECT c from Technology c where c.name like %:name%")
    List<Technology> findByName(@Param("name") String name);
}
