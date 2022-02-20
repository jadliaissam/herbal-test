package com.herbal.ws.response;

import com.herbal.ws.shared.dto.ProductsDto;
import com.herbal.ws.shared.dto.TypeCommandeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeResponse implements Serializable {

    private static final long serialVersionUID = 6540152208691607844L;

    private long id;

    private String idClientCommande;
    private String city;
    private String number;
    private int qte;
    private String fullNameClient;
    private boolean livraison;

    private boolean etatCommande;

    private Date dateCommande;

    private Date dateLivraison;

    private Date dateSucces;

    private Date dateRetour;

    private ProductsDto product;

    private TypeCommandeDto typeCommande;
}
