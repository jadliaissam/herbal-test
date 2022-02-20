package com.herbal.ws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DELL
 *
 */

@Entity(name = "users") @Data @NoArgsConstructor @AllArgsConstructor
public class UserEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8802014973706613559L;

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = true, length = 120)
    private String name;

    @Email
    @Column(nullable = false, length = 120)
    private String email;
    /*
        @Column(nullable = false)
        private String userId;

        @Column(nullable = true, length = 50)
        private String firstname;

        @Column(nullable = true, length = 150)
        private String lastname;

        @Column(nullable = true, length = 120)
        private String email;
    */
    @Column(nullable = true, length = 120)
    private String username;

    @Column(nullable = false)
    private String encryptedPassword;

    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<RoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy="user",cascade=CascadeType.PERSIST)
    private List<ProductsEntity> userAddingToStock;
/*
    @OneToMany(mappedBy="user",cascade=CascadeType.PERSIST)
    private List<StockEntity> saveAddToStock;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<ClotureDetailsEntity> clotureDetailsEntity;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<ConsomationStockEntity> consomationStock;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<EntreStockEntity> entreStock;
    */
}
