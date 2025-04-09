package com.quafresh.web.aquafreshweb.repositories;

import com.quafresh.web.aquafreshweb.entity.Product;
import com.quafresh.web.aquafreshweb.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT c from Product c where c.productName like %:name%")
    List<Product> findByName(@Param("name") String name);
}
