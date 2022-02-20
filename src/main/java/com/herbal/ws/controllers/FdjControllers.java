package com.herbal.ws.controllers;

import com.herbal.ws.response.CommandeResponse;
import com.herbal.ws.services.CommandeService;
import com.herbal.ws.shared.excelExport.ExportExcelCloture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/exportData") // localhost:8084/api/exportData
@RequiredArgsConstructor
public class FdjControllers {
    private final CommandeService commandeService;

    @GetMapping("/ExportExcel")
    public ModelAndView exportToExcel() {
        /*

        //ClotureDetailsDto clotureDetailsDto = clotureService.getClotureByPK(anneeCloture,moisCloture);
        //HashMap<String, List> clotureDetailsDtoInfo = clotureService.getClotureinfo(clotureDetailsDto);
        //ModelMapper modelMapper = new ModelMapper();
        //List<StockEntreResponse> stockEntreResponseType = new ArrayList<>();
        //List<StockConsomationResponse> stockConsomationResponses = new ArrayList<>();
        //List<StockConsomationResponse> stockConsomationResponsesPdr = new ArrayList<>();
        //List<StockResponse> stockResponses = new ArrayList<>();
        //FOR ENTRE
        //for (Object stock:  clotureDetailsDtoInfo.get("entre")) {
          //  StockEntreResponse helpersEntre =   modelMapper.map(stock,StockEntreResponse.class);
            //stockEntreResponseType.add(helpersEntre);
            //log.info("Helpers Entre {}",helpersEntre);
        //}
        //FOR VENTE
        for (Object stock:  clotureDetailsDtoInfo.get("vente")) {
            StockConsomationResponse helpersEntre =   modelMapper.map(stock,StockConsomationResponse.class);
            stockConsomationResponses.add(helpersEntre);
            log.info("Helpers Entre {}",helpersEntre);
        }
        // FOR PDR
        for (Object stock:  clotureDetailsDtoInfo.get("pdr")) {
            StockConsomationResponse helpersPDR =   modelMapper.map(stock,StockConsomationResponse.class);
            stockConsomationResponsesPdr.add(helpersPDR);
            log.info("Helpers PDR {}",helpersPDR);
        }
        // FOR ALL VALUE OF STOCK
        for (Object stock:  clotureDetailsDtoInfo.get("stockValue")) {
            StockResponse stockResponse = modelMapper.map(stock,StockResponse.class);
            stockResponses.add(stockResponse);
            log.info("Helpers stockResponse {}",stockResponse);
        }
        ModelAndView mav = new ModelAndView();
        mav.setView(new ExportExcelCloture());
        //read data from DB
        //send to excelImpl class
        mav.addObject("list", stockEntreResponseType);
        mav.addObject("vente", stockConsomationResponses);
        mav.addObject("pdr", stockConsomationResponsesPdr);
        mav.addObject("stockValue", stockResponses);
        return mav;

         */

        HashMap<String,List<CommandeResponse>> getCommandeInfo = commandeService.getCommandeInfo();
        ModelAndView mav = new ModelAndView();
        mav.setView(new ExportExcelCloture());
        mav.addObject("commandeInitial", getCommandeInfo.get("commandeInitial"));
        return mav;
    }
}
