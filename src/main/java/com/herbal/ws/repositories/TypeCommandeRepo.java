package com.herbal.ws.repositories;

import com.herbal.ws.entities.TypeCommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TypeCommandeRepo extends CrudRepository<TypeCommandeEntity, Long> {
    TypeCommandeEntity findById(long id);
}