package com.quafresh.web.aquafreshweb.controller.guess;

import com.quafresh.web.aquafreshweb.dto.guess.PictureGuessDTO;
import com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO;
import com.quafresh.web.aquafreshweb.dto.guess.ProductDetailGuessDTO2;
import com.quafresh.web.aquafreshweb.entity.*;
import com.quafresh.web.aquafreshweb.service.Impl.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/product")
public class HomeController {

    private final ProductDetailImpl productDetail;
    private final ColorServiceImpl colorServiceImpl;
    private final DiscountServiceImpl discountServiceImpl;
    private final CategoryServiceImpl categoryServiceImpl;
    private final TechnologyServiceImpl technologyServiceImpl;
    private final CompanyServiceImpl companyServiceImpl;
    private final PictureGuessServiceImpl pictureGuessService;

    public HomeController(ProductDetailImpl productDetail,
                          ColorServiceImpl colorServiceImpl,
                          DiscountServiceImpl discountServiceImpl,
                          CategoryServiceImpl categoryServiceImpl,
                          TechnologyServiceImpl technologyServiceImpl,
                          CompanyServiceImpl companyServiceImpl,
                          PictureGuessServiceImpl pictureGuessService) {
        this.productDetail = productDetail;
        this.colorServiceImpl = colorServiceImpl;
        this.discountServiceImpl = discountServiceImpl;
        this.categoryServiceImpl = categoryServiceImpl;
        this.technologyServiceImpl = technologyServiceImpl;
        this.companyServiceImpl = companyServiceImpl;
        this.pictureGuessService = pictureGuessService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDetail>> getAllProduct() {
        return productDetail.getAll();
    }

    @GetMapping("/picture")
    public ResponseEntity<List<PictureGuessDTO>> getAllPicture() {
        return pictureGuessService.getAllPicture();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDetailGuessDTO>> search(
            @RequestParam(required = false) Integer technologyId,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) BigDecimal priceFrom,
            @RequestParam(required = false) BigDecimal priceTo
    ) {
        return pictureGuessService.getAllPictureProductDetail(technologyId, productName, priceFrom, priceTo);
    }

    @GetMapping("/{id}")
    public ProductDetailGuessDTO2 getById(@PathVariable Integer id) {
        return productDetail.getById(id);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        return categoryServiceImpl.getAllCategory();
    }

    @GetMapping("/company")
    public ResponseEntity<List<Company>> getAllCompany() {
        return companyServiceImpl.getAllCompany();

    }

    @GetMapping("/color")
    public ResponseEntity<List<Color>> getAllColor() {
        return colorServiceImpl.getAllColor();

    }

    @GetMapping("/discount")
    public ResponseEntity<List<Discount>> getAllDiscount() {
        return discountServiceImpl.getAllDiscount();

    }

    @GetMapping("/technology")
    public ResponseEntity<List<Technology>> getAllCTechnology() {
        return technologyServiceImpl.getAllTechnology();
    }
}
