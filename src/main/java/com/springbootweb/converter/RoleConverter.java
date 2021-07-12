package com.springbootweb.converter;

import com.springbootweb.dto.RoleDTO;
import com.springbootweb.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO toDTO(RoleEntity roleEntity) {
        RoleDTO result = modelMapper.map(roleEntity, RoleDTO.class);
        return result;
    }

    public RoleEntity toEntity(RoleDTO roleDTO) {
        RoleEntity result = modelMapper.map(roleDTO, RoleEntity.class);
        return result;
    }
}
