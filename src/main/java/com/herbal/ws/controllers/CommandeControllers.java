package com.herbal.ws.controllers;

import com.herbal.ws.entities.CommandeEntity;
import com.herbal.ws.entities.ProductsEntity;
import com.herbal.ws.response.CommandeResponse;
import com.herbal.ws.response.ProductsResponse;
import com.herbal.ws.response.StockErrorMessage;
import com.herbal.ws.services.CommandeService;
import com.herbal.ws.shared.dto.CommandeDto;
import com.herbal.ws.shared.dto.ProductsDto;
import com.herbal.ws.userRequest.CommandeRequest;
import com.herbal.ws.userRequest.ProductsRequest;
import com.herbal.ws.userRequest.UpdatingCommande;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/commande") // localhost:8084/api/products
@RequiredArgsConstructor
public class CommandeControllers {

    private final CommandeService commandeService;

    @PostMapping(path = "/add_commande",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<HashMap<String,String>> addCommande(@RequestBody CommandeRequest commandeRequest) throws Exception {
        if (commandeRequest.getIdProducts().isEmpty() || commandeRequest.getFullNameClient().isEmpty() || commandeRequest.getNumber().isEmpty()
                || commandeRequest.getQte()<0)
            throw new Exception(StockErrorMessage.MISSING_REQUIRED_FILED.getErrorMessage());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        CommandeDto commandeDto = modelMapper.map(commandeRequest,CommandeDto.class);
        HashMap<String,String> addToDb = commandeService.addCommande(commandeDto);
        return new ResponseEntity<HashMap<String,String>>(addToDb, HttpStatus.CREATED);
    }

    @PutMapping(path = "/livraison/{commandeId}",produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CommandeResponse> updatedLivraison(@RequestBody UpdatingCommande updatingCommande, @PathVariable String commandeId) {
        // Khasni ndir Get LCh7al kayn f la base u ndir 3lih Modification b dkchi li
        // ghadi nisft f JSON
        // So awl haja hiya get ldkchi li ayji mn la base
        ModelMapper modelMapper = new ModelMapper();
        CommandeDto stockDtoUpdate = commandeService.updatedLivraison(commandeId,updatingCommande);
        CommandeResponse productsResponse = modelMapper.map(stockDtoUpdate, CommandeResponse.class);
        return new ResponseEntity<CommandeResponse>(productsResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/etat_commande/{commandeId}",produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CommandeResponse> updatedEtatCommande(@PathVariable String commandeId) {
        // Khasni ndir Get LCh7al kayn f la base u ndir 3lih Modification b dkchi li
        // ghadi nisft f JSON
        // So awl haja hiya get ldkchi li ayji mn la base
        ModelMapper modelMapper = new ModelMapper();
        CommandeDto stockDtoUpdate = commandeService.updatedCommande(commandeId);
        CommandeResponse productsResponse = modelMapper.map(stockDtoUpdate, CommandeResponse.class);
        return new ResponseEntity<CommandeResponse>(productsResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/info_commande", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity< HashMap<String,List<CommandeResponse>>> commandeInfo() {
        // an9albu 3lih
        HashMap<String,List<CommandeResponse>> getCommandeInfo = commandeService.getCommandeInfo();

        return new ResponseEntity<HashMap<String,List<CommandeResponse>>>(getCommandeInfo, HttpStatus.OK);
    }
    @GetMapping(path="/mainInfo/{dateD}/{dateF}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public  ResponseEntity<HashMap<String,Double>> getInfoToMain(@PathVariable String dateD, @PathVariable String dateF){
        HashMap<String,Double>  getInfoFromDb = commandeService.getInfoToDb(dateD,dateF);
        return new  ResponseEntity<HashMap<String,Double>>(getInfoFromDb,HttpStatus.ACCEPTED);
    }

}
