package com.herbal.ws.shared.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.herbal.ws.userRequest.ProductsRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDto implements Serializable {
    
    private static final long serialVersionUID = 6540152208691607844L;

    private long id;

    private String idClientCommande;

    private int qte;

    private String city;

    private String number;

    private boolean livraison;

    private String fullNameClient;

    private String  idProducts;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateCommande;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateLivraison;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateSucces;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateRetour;

    private boolean etatCommande;

    private ProductsDto product;

    private TypeCommandeDto typeCommande;
}
