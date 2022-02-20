package com.herbal.ws.userRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeRequest {

   private String idProducts;

   private int qte;

   private String fullNameClient;

   private String city;

   private String number;

   private Date dateCommande;



   private long typeCommande;
}
