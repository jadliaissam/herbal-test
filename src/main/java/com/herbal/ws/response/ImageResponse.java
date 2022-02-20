package com.herbal.ws.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse implements Serializable {
    private static final long serialVersionUID = 6540050208691607841L;

    private String idBrowserPhoto;
    private String title;

    private String description;

    private String imagePath;

    private String imageFileName;
}
