package com.herbal.ws.services;

import com.herbal.ws.entities.CommandeEntity;
import com.herbal.ws.response.CommandeResponse;
import com.herbal.ws.shared.dto.CommandeDto;
import com.herbal.ws.shared.dto.ProductsDto;
import com.herbal.ws.userRequest.UpdatingCommande;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CommandeService {
    HashMap<String,String> addCommande(CommandeDto commandeDto);

    CommandeDto updatedLivraison(String commandeId,UpdatingCommande updatingCommande);

    CommandeDto updatedCommande(String commandeId);

    HashMap<String,List<CommandeResponse>> getCommandeInfo();

    HashMap<String, Double> getInfoToDb(String dateD, String dateF);
}
