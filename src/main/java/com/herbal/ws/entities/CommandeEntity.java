package com.herbal.ws.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "commande") @Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeEntity {

    @Id
    @GeneratedValue
    private long idCommande;

    @Column(nullable = false, length = 52)
    private String idClientCommande;

    @Column(nullable = false, length = 52)
    private int qte;

    @Column(nullable = false)
    private boolean livraison;

    @Column(nullable = true)
    private boolean etatCommande ;

    @Column(nullable = false)
    private String fullNameClient;


    @Column(nullable = false)
    private String city;


    @Column(nullable = false)
    private String number;


    @Column(nullable = false)
    private Date dateCommande;

    @Column(nullable = true)
    private Date dateLivraison;

    @Column(nullable = true)
    private Date dateSucces;

    @Column(nullable = true)
    private Date dateRetour;

    // ONE COMMANDE HAS ONE PRODUCTS OR MANY
    // products has many commande or one
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductsEntity product;

    @ManyToOne
    @JoinColumn(name="commande_id")
    private TypeCommandeEntity typeCommande;

}
