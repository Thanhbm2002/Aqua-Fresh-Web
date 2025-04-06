package com.quafresh.web.aquafreshweb.controller.guess;

import com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO;
import com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO2;
import com.quafresh.web.aquafreshweb.entity.Picture;
import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import com.quafresh.web.aquafreshweb.service.Impl.PictureGuessServiceImpl;
import com.quafresh.web.aquafreshweb.service.Impl.ProductDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class HomeController {

    @Autowired
    ProductDetailImpl productDetail;
    @Autowired
    PictureGuessServiceImpl pictureGuessService;
    @GetMapping
    public ResponseEntity<List<ProductDetail>> getAllProduct(){
        return productDetail.getAll();
    }
    @GetMapping("/picture")
    public ResponseEntity<List<Picture>> getAllPicture(){
        return pictureGuessService.getAllPicture();
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDetailGuessDTO>> search(
            @RequestParam(required = false) Integer technologyId,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) BigDecimal priceFrom,
            @RequestParam(required = false) BigDecimal priceTo
    ){
        return pictureGuessService.getAllPictureProductDetail(technologyId, productName, priceFrom, priceTo);
    }
    @GetMapping("/{id}")
    public ProductDetailGuessDTO2 getById(@PathVariable Integer id){
        return productDetail.getById(id);
    }
}
