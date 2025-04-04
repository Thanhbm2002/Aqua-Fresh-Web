package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;

@Builder
public class PictureAdimDTO {
    private Integer id;
    private String urlImage;
    private ProductAdminDTO idProduct;
}
