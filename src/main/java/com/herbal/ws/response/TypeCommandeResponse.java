package com.herbal.ws.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeCommandeResponse {

    private long id;

    private String idClientCommande;

    private String signification;
}
