package com.quafresh.web.aquafreshweb.util;

import com.quafresh.web.aquafreshweb.dto.LoginDTO;
import com.quafresh.web.aquafreshweb.dto.LoginRequestDTO;
import com.quafresh.web.aquafreshweb.dto.admin.*;
import com.quafresh.web.aquafreshweb.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    ProductAdminDTO toProductAdminDTO(Product product);

    ColorAdminDtO toColorAdminDTO(Color color);

    TechnologyAdminDTO toTechnologyAdminDTO(Technology tech);

    DiscountAdminDTO toDiscountAdminDTO(Discount discount);

    PictureAdimDTO toPictureAdminDTO(Picture picture);

    ProductDetailAdminDTO toProductDetailAdminDTO(ProductDetail productDetail);

    CompanyAdminDTO toCompanyAdminDTO(Company company);

    CategoryAdminDTO toCategoryAdminDTO(Category category);
     CustomerDTO toCustomerDTO(User customer);
     LoginRequestDTO toLoginRequestDTO(User user);
}

