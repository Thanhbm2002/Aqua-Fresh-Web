package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PictureAdimDTO {
    private Integer id;
    private String urlImage;
    private ProductAdminDTO idProduct;
}
