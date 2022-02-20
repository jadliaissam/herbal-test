package com.herbal.ws.services;

import com.herbal.ws.entities.CategorieEntity;
import com.herbal.ws.shared.dto.CategoryDto;
import com.herbal.ws.userRequest.CategoryRequest;

import java.util.List;

public interface CategorieService {

    CategoryDto save(CategoryDto category);
    List<CategoryDto> findAll();
    CategorieEntity findCategoryByName(String name);
    CategorieEntity findCategoryById(Long id);
    List<CategorieEntity> getAllCategories();
    Boolean existsByNomCategory(String name);
    void removeCategory(CategorieEntity category);

    CategoryDto updateCategory(String categorieId, CategoryRequest categoryRequest);

    void deletedArticle(String clientId);
}
