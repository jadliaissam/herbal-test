package com.herbal.ws.serviceImp;

import com.herbal.ws.entities.*;
import com.herbal.ws.repositories.*;
import com.herbal.ws.services.ImageService;
import com.herbal.ws.services.ProductsService;
import com.herbal.ws.shared.Utils;
import com.herbal.ws.shared.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Slf4j @Service @Transactional
public class ProductsServiceImp implements ProductsService {

private final UserRepo userRepo;
private final ProductsRepo productsRepo;
private final Utils utils;
private final ImageRepo imageRepo;
private final CategorieRepo categorieRepo;
	/*
	@Override
	public StockDto addArticleToStock(StockDto stockDto, String email) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity currentUser = userRepository.findByEmail(email);
	//	log.info("The Current User is {}",currentUser);
		StockEntity stockEntity =modelMapper.map(stockDto, StockEntity.class);
		stockEntity.setUser(currentUser);
		stockEntity.setClientId(utils.generateStringId(23));
		StockEntity EntityAddArticleToStock = stockRepository.save(stockEntity);
		StockDto stockDtoAddArticleToStock = modelMapper.map(EntityAddArticleToStock, StockDto.class);
		return stockDtoAddArticleToStock;
	}
	 */
	@Override
	public ProductsDto addProductsTodb(ProductsDto productsDto, String email,String idClientPhoto, String idCategoryClient) {
		ModelMapper modelMapper = new ModelMapper();
		ImageEntity imageEntityToProducts = imageRepo.findByIdBrowserPhoto(idClientPhoto);
		UserEntity currentUser = userRepo.findByUsername(email);
		CategorieEntity categorieEntity=categorieRepo.findCategoryByIdCategoryClient(idCategoryClient);
		ProductsEntity productsEntity = new ProductsEntity();
		productsEntity.setTitleArticle(productsDto.getTitleArticle());
		productsEntity.setDescriptionArticle(productsDto.getDescriptionArticle());
		productsEntity.setPu(productsDto.getPu());
		productsEntity.setImageProduct(imageEntityToProducts);
		productsEntity.setCategoryProduct(categorieEntity);
		productsEntity.setUser(currentUser);
		productsEntity.setIdClientProducts(utils.generateStringId(23));
		ProductsEntity EntityAddArticleToDb = productsRepo.save(productsEntity);
		ProductsDto productsDtoAddArticleToDb = modelMapper.map(EntityAddArticleToDb, ProductsDto.class);
		return productsDtoAddArticleToDb;
	}

	@Override
	public List<ProductsDto> getArticle(int page, int limit, String article) {
		if (page>0) {
			page = page -1;
		}
		// Wslni daba dkchi li dayaz f url
		// Ghadi N7et array khawi bach n3mru b les infos li ghadi itla7u controllers
		ModelMapper modelmapper = new ModelMapper();
		List<ProductsDto> productsDto = new ArrayList<>();
		// le type Pageable huwa li kaydir lik lkhdma dial t9sim dial page b methodes PageRequest.of(page, limit)
		Pageable pagaebaleRequest = PageRequest.of(page, limit);
		Page<ProductsEntity> productsEntities;
		productsEntities = productsRepo.findAll(pagaebaleRequest);
		// Kanakhud dkchi li kayn f page kandawzu Array

		List<ProductsEntity> stockEntityContent = productsEntities.getContent();
		// Nefs lkhdma bhal controllers boucle bach nakhdu les infos ndawzhum L DTO
		for(ProductsEntity StockEntityArticle : stockEntityContent) {
			ProductsDto stockDtoArticle = modelmapper.map(StockEntityArticle, ProductsDto.class);
			//BeanUtils.copyProperties(StockEntityArticle, stockDtoArticle);
			productsDto.add(stockDtoArticle);
		}

		return productsDto;
	}

	@Override
	public ProductsEntity getArticleFromDb(String idClient) {
		ProductsEntity productsEntity = productsRepo.findByIdClientProducts(idClient);
		if(productsEntity==null) throw new RuntimeException("Article Exixt pas");
		//ModelMapper modelMapper = new ModelMapper();
		//ProductsDto stockDtogetByArticle =modelMapper.map(productsEntity, ProductsDto.class);
		return productsEntity;
	}

	@Override
	public ProductsDto updatedProducts(String clientId, ProductsEntity productsDto, ProductsDto newProductsRequest, String name,String categorie) {
		ModelMapper modelMapper = new ModelMapper();
		// MAPPING TO ENTITY
        CategorieEntity categorieEntity = categorieRepo.findCategoryByIdCategoryClient(categorie);
        if(!categorie.isEmpty()){
            productsDto.setCategoryProduct(categorieEntity);
        }
		if(!newProductsRequest.getTitleArticle().isEmpty())
		{
			productsDto.setTitleArticle(newProductsRequest.getTitleArticle());
		}

		if(!newProductsRequest.getDescriptionArticle().isEmpty())
		{
			productsDto.setDescriptionArticle(newProductsRequest.getDescriptionArticle());
		}

		if(newProductsRequest.getPu()>0)
		{
			productsDto.setPu(newProductsRequest.getPu());
		}
		productsRepo.save(productsDto);
		ProductsEntity ProductAfterUpdated = productsRepo.save(productsDto);

		//MAPPING ENTITY TO DTO
		ProductsDto productsDto1 = modelMapper.map(ProductAfterUpdated,ProductsDto.class);

		return productsDto1;
	}

	@Override
	public void deletedArticle(String clientId) {
		ProductsEntity stockEntity = productsRepo.findByIdClientProducts(clientId);
		productsRepo.delete(stockEntity);
	}

	@Override
	public List<ProductsDto> getArticleByCategory(String clientId) {
		// Wslni daba dkchi li dayaz f url
		// Ghadi N7et array khawi bach n3mru b les infos li ghadi itla7u controllers
		ModelMapper modelmapper = new ModelMapper();
		CategorieEntity categorieEntity = categorieRepo.findCategoryByIdCategoryClient(clientId);
		List<ProductsDto> productsDto = new ArrayList<>();
		// le type Pageable huwa li kaydir lik lkhdma dial t9sim dial page b methodes PageRequest.of(page, limit)
		List<ProductsEntity> productsEntities = productsRepo.findByCategoryProduct(categorieEntity);

		// Kanakhud dkchi li kayn f page kandawzu Array

		//List<ProductsEntity> stockEntityContent = productsEntities.getContent();
		// Nefs lkhdma bhal controllers boucle bach nakhdu les infos ndawzhum L DTO
		for(ProductsEntity StockEntityArticle : productsEntities) {
			ProductsDto stockDtoArticle = modelmapper.map(StockEntityArticle, ProductsDto.class);
			//BeanUtils.copyProperties(StockEntityArticle, stockDtoArticle);
			productsDto.add(stockDtoArticle);
		}
		return productsDto;
	}
}
