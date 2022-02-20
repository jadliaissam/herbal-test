package com.herbal.ws.services;

import com.herbal.ws.entities.ProductsEntity;
import com.herbal.ws.shared.dto.*;

import java.util.HashMap;
import java.util.List;

public interface ProductsService {

	ProductsDto addProductsTodb(ProductsDto productsDto,String email,String idClientPhoto,String idCategoryClient);

	List<ProductsDto> getArticle(int page, int limit, String article);
	ProductsEntity getArticleFromDb(String idClient);

	ProductsDto updatedProducts(String clientId, ProductsEntity productsDto, ProductsDto newProductsRequest, String name,String categorie);

	void deletedArticle(String clientId);

    List<ProductsDto> getArticleByCategory(String clientId);
	/*
	StockDto getArticleFromStock(String id);
	StockDto updateStock(String clientId,StockDto stockDto,StockDto nombreDecrementstockDto,StockConsomationDto stockConsomationdDto, String email );
	void deletedArticle(String clientId);
	List<StockDto> getArticle(int page,int limit,String article);
	List<StockConsomationDto> getAllConsomationStock(int page, int limit);
	StockDto entreStock(String clientId, StockDto stockDto, StockDto nombreDecrementstockDto, String email);
	List<EntreStockDto> getAllArticleFromEntreStock(int page, int limit);
	HashMap<String , Double> getInformationStock();
	List<CassierDto> getCasier();

	 */

}