package com.quafresh.web.aquafreshweb.dto.guess;

import com.quafresh.web.aquafreshweb.entity.Picture;
import lombok.Data;

@Data
public class PictureGuessDTO {
    private Integer id;
    private String urlImage;
    private Boolean status;
    private Integer productDetailIdl;

    public PictureGuessDTO(Picture picture) {
        this.id = picture.getId();
        this.urlImage = picture.getUrlImage();
        this.status = picture.getStatus();
        this.productDetailIdl = picture.getIdProductDetail().getId();
    }
}
