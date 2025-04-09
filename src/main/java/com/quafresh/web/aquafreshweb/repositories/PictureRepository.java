package com.quafresh.web.aquafreshweb.repositories;

import com.quafresh.web.aquafreshweb.dto.ProductDetailDTO;
import com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO;
import com.quafresh.web.aquafreshweb.entity.Picture;
import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    @Query("""
    select new com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO(
        p.id, p.urlImage, p.status,
        pd.id, prod.productName, pd.price, pd.quantity, pd.description, pd.status
    )
    from Picture p
    join p.idProductDetail pd
    join pd.idProduct prod
    left join pd.idTechnology tech
    where (:technologyId is null or tech.id = :technologyId)
    and (:productName is null or lower(prod.productName) like lower(concat('%', :productName, '%')))
    and (:priceFrom is null or pd.price >= :priceFrom)
    and (:priceTo is null or pd.price <= :priceTo)
""")
    List<ProductDetailGuessDTO> searchByFilters(
            @Param("technologyId") Integer technologyId,
            @Param("productName") String productName,
            @Param("priceFrom") BigDecimal priceFrom,
            @Param("priceTo") BigDecimal priceTo
    );

    @Query ("""
    select new com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO(
    p.id, p.urlImage, p.status,
        pd.id, prod.productName, pd.price, pd.quantity, pd.description, pd.status
    )from Picture p
    join p.idProductDetail pd
    join pd.idProduct prod
    left join pd.idTechnology tech
    where (:id is null or pd.id = :id)
""")
    List<ProductDetailGuessDTO> getByProductId(@Param("id") Integer id);

    List<Picture> findByIdProductDetail(ProductDetail productDetail);


}
