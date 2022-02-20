package com.herbal.ws.repositories;

import com.herbal.ws.entities.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepo extends JpaRepository<CategorieEntity, Long> {
    CategorieEntity findCategoryById(Long id);
    CategorieEntity findCategoryByNomCategory(String name);
    CategorieEntity findCategoryByIdCategoryClient(String idCategoryClient);
    boolean existsByNomCategory(String name);
}