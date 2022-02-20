package com.herbal.ws.serviceImp;


import com.herbal.ws.entities.CommandeEntity;
import com.herbal.ws.entities.ProductsEntity;
import com.herbal.ws.entities.TypeCommandeEntity;
import com.herbal.ws.repositories.CommandeRepo;
import com.herbal.ws.repositories.ProductsRepo;
import com.herbal.ws.repositories.TypeCommandeRepo;
import com.herbal.ws.response.CommandeResponse;
import com.herbal.ws.response.StockErrorMessage;
import com.herbal.ws.services.CommandeService;
import com.herbal.ws.shared.Utils;
import com.herbal.ws.shared.dto.CommandeDto;
import com.herbal.ws.shared.dto.ProductsDto;
import com.herbal.ws.userRequest.UpdatingCommande;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CommandeServiceImp implements CommandeService {
    private final CommandeRepo commandeRepo;
    private final ProductsRepo productsRepo;
    private final Utils utils;
    private final TypeCommandeRepo typeCommandeRepo;
    @Override
    public HashMap<String,String> addCommande(CommandeDto commandeDto) {
        ProductsEntity getProductsOfCommande = productsRepo.findByIdClientProducts(commandeDto.getIdProducts());
        TypeCommandeEntity tce= typeCommandeRepo.findById(1);
        log.info("getProductsOfCommande",getProductsOfCommande);
        CommandeEntity commandeEntity = new CommandeEntity();
        commandeEntity.setProduct(getProductsOfCommande);
        commandeEntity.setLivraison(false);
        commandeEntity.setNumber(commandeDto.getNumber());
        commandeEntity.setIdClientCommande(utils.generateStringId(20));
        commandeEntity.setCity(commandeDto.getCity());
        commandeEntity.setFullNameClient(commandeDto.getFullNameClient());
        commandeEntity.setQte(commandeDto.getQte());
        commandeEntity.setDateCommande(commandeDto.getDateCommande());
        commandeEntity.setTypeCommande(tce);
        commandeRepo.save(commandeEntity);
        HashMap<String,String> messageSucces = new HashMap<String,String>();
        messageSucces.put("MESSAGE_SUCCES","VOUS AVEZ AJOUTER AVEC SUCES LA COMMANDE");
        return messageSucces;
    }

    @Override
    public CommandeDto updatedLivraison(String commandeId,UpdatingCommande updatingCommande) {
        ModelMapper modelMapper = new ModelMapper();
        TypeCommandeEntity tce= typeCommandeRepo.findById(updatingCommande.getEtatCommande());
        CommandeEntity commandeEntityToUpdateLivraison  = commandeRepo.findByIdClientCommande(commandeId);
        commandeEntityToUpdateLivraison.setLivraison(true);
        commandeEntityToUpdateLivraison.setTypeCommande(tce);
        if(updatingCommande.getEtatCommande()==2) commandeEntityToUpdateLivraison.setDateLivraison(updatingCommande.getDateLivraison());
        if(updatingCommande.getEtatCommande()==3) commandeEntityToUpdateLivraison.setDateSucces(updatingCommande.getDateSucces());
        if(updatingCommande.getEtatCommande()==4) commandeEntityToUpdateLivraison.setDateRetour(updatingCommande.getDateRetour());
        commandeRepo.save(commandeEntityToUpdateLivraison);
        CommandeDto commandeDto = modelMapper.map(commandeEntityToUpdateLivraison,CommandeDto.class);
        return commandeDto;
    }

    @Override
    public CommandeDto updatedCommande(String commandeId) {
        ModelMapper modelMapper = new ModelMapper();
        CommandeEntity commandeEntityToUpdateLivraison  = commandeRepo.findByIdClientCommande(commandeId);
        log.info("**************************************************************************************************");
        log.info("commandeEntityToUpdateLivraison.isLivraison() {}",commandeEntityToUpdateLivraison.isLivraison());
        log.info("**************************************************************************************************");
        if (commandeEntityToUpdateLivraison.isLivraison()!=true) throw new RuntimeException(StockErrorMessage.MISSING_REQUIRED_FILED.getErrorMessage());
        commandeEntityToUpdateLivraison.setEtatCommande(true);
        commandeRepo.save(commandeEntityToUpdateLivraison);
        CommandeDto commandeDto = modelMapper.map(commandeEntityToUpdateLivraison,CommandeDto.class);
        return commandeDto;
    }
    @Override
    public HashMap<String,List<CommandeResponse>> getCommandeInfo() {
        ModelMapper modelMapper = new ModelMapper();
        HashMap<String,List<CommandeResponse>> listHashMap = new HashMap<String,List<CommandeResponse>>();
        // GET NOT SHIPPED
        long typeCI= 1;
        long typeCL= 2;
        long typeCS= 3;
        long typeCR= 4;
        TypeCommandeEntity ci= typeCommandeRepo.findById(typeCI);
        TypeCommandeEntity cl= typeCommandeRepo.findById(typeCL);
        TypeCommandeEntity cs= typeCommandeRepo.findById(typeCS);
        TypeCommandeEntity cr= typeCommandeRepo.findById(typeCR);
        List<CommandeEntity> tceIntilial= commandeRepo.findByTypeCommande(ci);
        List<CommandeEntity> tceLivraison = commandeRepo.findByTypeCommande(cl);
        List<CommandeEntity> tceSucces = commandeRepo.findByTypeCommande(cs);
        List<CommandeEntity> tceRetour = commandeRepo.findByTypeCommande(cr);




        // Convert to   DTO
        List<CommandeDto> commandeDtos = new ArrayList<>();

        for (CommandeEntity commandeEntity: tceIntilial) {
            CommandeDto commandeDto = modelMapper.map(commandeEntity,CommandeDto.class);
            commandeDtos.add(commandeDto);
        }

        // CONVERT TO RESPONSE
        List<CommandeResponse> commandeResponses = new ArrayList<>();
        for (CommandeDto commandeDto: commandeDtos) {
            CommandeResponse commandeResponse = modelMapper.map(commandeDto,CommandeResponse.class);
            commandeResponses.add(commandeResponse);
        }

        //GET SHIPPED AND VALID
     //   List<CommandeEntity> commadeSucces = commandeRepo.findByLivraisonAndEtatCommande(true,true);


        // Convert to   DTO
        List<CommandeDto> commandeDtosucces = new ArrayList<>();

        for (CommandeEntity commandeEntity: tceLivraison) {
            CommandeDto commandeDto = modelMapper.map(commandeEntity,CommandeDto.class);
            commandeDtosucces.add(commandeDto);
        }
        // CONVERT TO RESPONSE

        List<CommandeResponse> commandeResponsesuccesLivraison = new ArrayList<>();
        for (CommandeDto commandeDto: commandeDtosucces) {
            CommandeResponse commandeResponse = modelMapper.map(commandeDto,CommandeResponse.class);
            commandeResponsesuccesLivraison.add(commandeResponse);
        }

        //GET RETOUR
     //   List<CommandeEntity> commadeRetour = commandeRepo.findByLivraisonAndEtatCommande(true,false);
        List<CommandeDto> commandeDtoRetour = new ArrayList<>();

        for (CommandeEntity commandeEntity: tceRetour) {
            CommandeDto commandeDto = modelMapper.map(commandeEntity,CommandeDto.class);
            commandeDtoRetour.add(commandeDto);
        }
        // CONVERT TO RESPONSE

        List<CommandeResponse> commandeResponseRetour = new ArrayList<>();
        for (CommandeDto commandeDto: commandeDtoRetour) {
            CommandeResponse commandeResponse = modelMapper.map(commandeDto,CommandeResponse.class);
            commandeResponseRetour.add(commandeResponse);
        }

        //GET RETOUR
       // List<CommandeEntity> commadeRetour = commandeRepo.findByLivraisonAndEtatCommande(true,false);
        List<CommandeDto> commandeSucces = new ArrayList<>();

        for (CommandeEntity commandeEntity: tceSucces) {
            CommandeDto commandeDto = modelMapper.map(commandeEntity,CommandeDto.class);
            commandeSucces.add(commandeDto);
        }
        // CONVERT TO RESPONSE

        List<CommandeResponse> commandeResponseSucces = new ArrayList<>();
        for (CommandeDto commandeDto: commandeSucces) {
            CommandeResponse commandeResponse = modelMapper.map(commandeDto,CommandeResponse.class);
            commandeResponseSucces.add(commandeResponse);
        }

        listHashMap.put("commandeInitial",commandeResponses);
        listHashMap.put("commandeLivraison",commandeResponsesuccesLivraison);
        listHashMap.put("commandeSucces",commandeResponseSucces);
        listHashMap.put("commandeRetour",commandeResponseRetour);
        return listHashMap;
    }

    @Override
    public HashMap<String, Double> getInfoToDb(String dateD, String dateF) {
        HashMap<String,Double> hashMap = new  HashMap<String,Double>();
        Double nmbrCI = 0.0;
        Double nmbrCL= 0.0;
        Double nmbrCR=0.0;
        if (commandeRepo.commandeEnmodeInitial(dateD,dateF)!=null){
            nmbrCI=commandeRepo.commandeEnmodeInitial(dateD,dateF);
        }

        if (commandeRepo.commandeEnmodeLivraison(dateD,dateF)!=null){
            nmbrCL=commandeRepo.commandeEnmodeLivraison(dateD,dateF);
        }

        if (commandeRepo.commandeEnmodeRetour(dateD,dateF)!=null){
            nmbrCR=commandeRepo.commandeEnmodeRetour(dateD,dateF);
        }

      //  Double nmbrCL = commandeRepo.commandeEnmodeLivraison(dateD,dateF);
      //  Double nmbrCR = commandeRepo.commandeEnmodeRetour(dateD,dateF);
        Double SumCI = 0.0;
        Double SumCL = 0.0;
        Double SumCR = 0.0;
        Double SumCS = 0.0;
        if(commandeRepo.sumCommandeEnmodeInitial(dateD,dateF)!=null){
            SumCI= commandeRepo.sumCommandeEnmodeInitial(dateD,dateF);
        }
        if(commandeRepo.sumCommandeEnmodeLivraison(dateD,dateF)!=null){
            SumCL= commandeRepo.sumCommandeEnmodeLivraison(dateD,dateF);
        }
        if(commandeRepo.sumCommandeEnmodeRetour(dateD,dateF)!=null){
            SumCR= commandeRepo.sumCommandeEnmodeRetour(dateD,dateF);
        }
        if(commandeRepo.sumCommandeEnmodeSucces(dateD,dateF)!=null){
            SumCS= commandeRepo.sumCommandeEnmodeSucces(dateD,dateF);
        }
        hashMap.put("commandeInitial",nmbrCI);
        hashMap.put("commandeLivraison",nmbrCL);
        hashMap.put("commandeRetour",nmbrCR);
        hashMap.put("caCI",SumCI);
        hashMap.put("caCl",SumCL);
        hashMap.put("caCR",SumCR);
        hashMap.put("caCS",SumCS);
        return hashMap;
    }
}