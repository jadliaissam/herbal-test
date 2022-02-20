package com.herbal.ws.repositories;

import com.herbal.ws.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByName(String name);
}
