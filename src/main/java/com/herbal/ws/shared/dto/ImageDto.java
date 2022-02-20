package com.herbal.ws.shared.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private static final long serialVersionUID = 654001020269160181L;

    private String idBrowserPhoto;
    private String title;

    private String description;

    private String imagePath;

    private String imageFileName;
}
