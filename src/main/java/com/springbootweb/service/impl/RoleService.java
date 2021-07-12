package com.springbootweb.service.impl;

import com.springbootweb.converter.RoleConverter;
import com.springbootweb.dto.RoleDTO;
import com.springbootweb.entity.RoleEntity;
import com.springbootweb.repository.RoleRepository;
import com.springbootweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO> findByCode(List<String> codes) {
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (String code: codes) {
            roleEntities.add(roleRepository.findOneByCode(code));
        }
        List<RoleDTO> result = new ArrayList<>();
        for (RoleEntity entity: roleEntities) {
            result.add(roleConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        RoleEntity result = roleConverter.toEntity(roleDTO);
        return roleConverter.toDTO(roleRepository.save(result));
    }

    @Override
    public void delete(long[] ids) {
        for (long id: ids) {
            roleRepository.delete(id);
        }
    }
}
