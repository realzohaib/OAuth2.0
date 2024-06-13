package com.erp.api.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.api.security.entity.ERole;
import com.erp.api.security.entity.RoleEntity;



@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
  Optional<RoleEntity> findByName(ERole name);
}
