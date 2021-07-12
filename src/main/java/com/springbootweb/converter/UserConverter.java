package com.springbootweb.converter;

import com.springbootweb.dto.UserDTO;
import com.springbootweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toDTO(UserEntity userEntity) {
        UserDTO result = modelMapper.map(userEntity, UserDTO.class);
        return result;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity result = modelMapper.map(userDTO, UserEntity.class);
        return result;
    }
}
