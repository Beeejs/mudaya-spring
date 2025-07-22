package com.mudaya.mudaya.utils;

import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.enums.UserRol;
import com.mudaya.mudaya.data.repositories.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleUtils {

    private final RoleRepository roleRepository;

    public RoleUtils(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getEstablishedRole(UserRol roleName) {
        return roleRepository.findByName(roleName != null ? roleName : UserRol.STAFF)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }
}
