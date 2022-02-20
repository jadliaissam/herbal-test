package com.herbal.ws.services;


import com.herbal.ws.entities.RoleEntity;
import com.herbal.ws.entities.UserEntity;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity roleName);
    void addRoleToUser(String username,String role);
    UserEntity getUser(String username);
}
