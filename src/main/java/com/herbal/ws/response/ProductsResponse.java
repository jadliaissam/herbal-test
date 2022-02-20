package com.herbal.ws.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponse {

    private String titleArticle;

    private String idClientProducts;

    private String descriptionArticle;

    private double pu;

    private int reduction;

    private String blogingArticle;

    private ImageResponse imageProduct;

    private CategoryResponse categoryProduct;

}
