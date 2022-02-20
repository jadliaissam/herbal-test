package com.herbal.ws.controllers;


import com.herbal.ws.entities.ProductsEntity;
import com.herbal.ws.response.ProductsResponse;
import com.herbal.ws.response.StockErrorMessage;
import com.herbal.ws.services.ProductsService;
import com.herbal.ws.shared.dto.ProductsDto;
import com.herbal.ws.userRequest.ProductsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/products") // localhost:8084/api/products
@RequiredArgsConstructor
public class ProductControllers {

    private final ProductsService productService;

    @PostMapping(path = "/add_article/{idClientPhoto}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ProductsResponse> createArticle(@RequestBody @Valid ProductsRequest productsRequest , Principal authentication,@PathVariable String idClientPhoto) throws Exception {
        if (productsRequest.getTitleArticle().isEmpty() || productsRequest.getDescriptionArticle().isEmpty()
                || productsRequest.getPu() <= 0)
            throw new Exception(StockErrorMessage.MISSING_REQUIRED_FILED.getErrorMessage());
        ModelMapper modelMapper = new ModelMapper();
        ProductsDto productsDto = modelMapper.map(productsRequest, ProductsDto.class);
        log.info("authentication getName {}", authentication.getName());
        ProductsDto addToDb = productService.addProductsTodb(productsDto, authentication.getName(), idClientPhoto, productsRequest.getIdCategoryClient());
        log.info("Stock DTO : {}", addToDb);
        ProductsResponse productsResponse = modelMapper.map(addToDb, ProductsResponse.class);
        //	URI uri  =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/stock/add_article").toUriString());
        //	return ResponseEntity.created(uri).body(stockResponse);
        return new ResponseEntity<ProductsResponse>(productsResponse, HttpStatus.CREATED);
    }

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ProductsResponse>> getAllArticle(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "90") int limit, @RequestParam(value="article",defaultValue="40") String article) {
        ModelMapper modelMapper= new ModelMapper();
        // Les articles kaynin bzzf So Ghadi n7athum f Array
        // Response diali ghadi n7atu de Type Array
        List<ProductsResponse> AllArticleResponse = new ArrayList<>();
        // Hnaya ghadi ndawz Service diali VALUE LI GHADI TJI MN URL
        List<ProductsDto> allArticleFromStock = productService.getArticle(page, limit,article);
        // db ghadi tjini response 3la cde type DTO Khasni n7awalha De type Response U
        // 3ndi Array Ki ghadi ndir ??
        // Ghadi ndir boucle li ghadi ndir des interations 3la dkchi li jani mn service
        // u dkchi li ghadi iwslni n7atu f Response
        for (ProductsDto stockDto : allArticleFromStock) {
            ProductsResponse productsResponse =modelMapper.map(stockDto, ProductsResponse.class);
            AllArticleResponse.add(productsResponse);
        }
        // Sf Array Ra 3mer b les Informations
        return new ResponseEntity<List<ProductsResponse>>(AllArticleResponse,HttpStatus.OK);
    }


    @GetMapping(path = "single/{clientId}",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ProductsResponse> getSingleArticle(@PathVariable String clientId) {
        ModelMapper modelMapper= new ModelMapper();
        ProductsEntity productsDto = productService.getArticleFromDb(clientId);
      ProductsResponse productsResponse=  modelMapper.map(productsDto,ProductsResponse.class);
        // Sf Array Ra 3mer b les Informations
        return new ResponseEntity<ProductsResponse>(productsResponse,HttpStatus.OK);
    }

    @GetMapping(path = "/product_category/{clientId}",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ProductsResponse>> getProductsByCategory(@PathVariable String clientId) {
        ModelMapper modelMapper= new ModelMapper();

        List<ProductsResponse> allArticleResponseByCategory = new ArrayList<>();
        List<ProductsDto> allArticleFromStock = productService.getArticleByCategory(clientId);

        for (ProductsDto stockDto : allArticleFromStock) {
            ProductsResponse productsResponse =modelMapper.map(stockDto, ProductsResponse.class);
            allArticleResponseByCategory.add(productsResponse);
        }
        // Sf Array Ra 3mer b les Informations
        return new ResponseEntity<List<ProductsResponse>>(allArticleResponseByCategory,HttpStatus.OK);
    }



    @PutMapping(path = "/entre/{clientId}", consumes = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ProductsResponse> entreStock(@PathVariable String clientId,
                                                    @RequestBody ProductsRequest productsRequest,Principal authUser) {
        // Khasni ndir Get LCh7al kayn f la base u ndir 3lih Modification b dkchi li
        // ghadi nisft f JSON
        // So awl haja hiya get ldkchi li ayji mn la base
        ModelMapper modelMapper = new ModelMapper();
        ProductsEntity productsDto = productService.getArticleFromDb(clientId);
        log.info("Product DTO {}",productsDto.getDescriptionArticle());
      //  ProductsEntity productsEntity = modelMapper.map(productsDto,ProductsEntity.class);
        ProductsDto newProductsRequest =modelMapper.map(productsRequest, ProductsDto.class);
        ProductsDto stockDtoUpdate = productService.updatedProducts(clientId, productsDto, newProductsRequest,authUser.getName(),productsRequest.getIdCategoryClient());
        ProductsResponse productsResponse = modelMapper.map(stockDtoUpdate, ProductsResponse.class);
        return new ResponseEntity<ProductsResponse>(productsResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete_product/{clientId}")
    public ResponseEntity<Object> deleteArticle(@PathVariable String clientId) {
        productService.deletedArticle(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
