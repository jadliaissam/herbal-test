package com.herbal.ws.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "products") @Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {

    @Id
    @GeneratedValue
    private long idProducts;

    @Column(nullable = false, length = 52)
    private String idClientProducts;

    @Column(nullable = false, length = 255)
    private String titleArticle;

    @Column(nullable = false, length = 255)
    private String descriptionArticle;

    @Column(nullable = false, length = 52)
    private double pu;

    @Column(nullable = true, length = 52)
    private double oldPrice;

    @ManyToOne
    @JoinColumn(name="image_id")
    private ImageEntity imageProduct;

    @Column(nullable = true, length = 250)
    private String blogingArticle;

    @Column(nullable = true, length = 250)
    private int reviewsArticle;

    @OneToMany(mappedBy="product",cascade=CascadeType.ALL)
    private List<CommandeEntity> commande;

    @ManyToOne
    @JoinColumn(name="users_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategorieEntity categoryProduct;
}