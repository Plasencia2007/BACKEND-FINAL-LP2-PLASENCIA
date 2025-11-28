package com.example.bdv1.repository.security;

import com.example.bdv1.entity.security.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrantedPermissionRepository extends JpaRepository<GrantedPermission, Long> {

    List<GrantedPermission> findByRolId(Long rolId);
}