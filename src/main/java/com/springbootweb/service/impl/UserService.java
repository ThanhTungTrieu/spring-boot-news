package com.springbootweb.service.impl;

import com.springbootweb.converter.UserConverter;
import com.springbootweb.dto.UserDTO;
import com.springbootweb.entity.RoleEntity;
import com.springbootweb.entity.UserEntity;
import com.springbootweb.repository.RoleRepository;
import com.springbootweb.repository.UserRepository;
import com.springbootweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        List<RoleEntity> role = new ArrayList<>();
        for (String code: userDTO.getRoles()) {
            role.add(roleRepository.findOneByCode(code));
        }
        UserEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setRoles(role);
        return userConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO findByUsernameAndPassword(String username, String password) {
        UserEntity userEntity = userRepository.findByUsernameAndPassword(username, password);
        UserDTO userDTO = new UserDTO();
        List<String> roles = new ArrayList<>();
        for (RoleEntity roleEntity: userEntity.getRoles()) {
            roles.add(roleEntity.getCode());
            System.out.println(roleEntity.getCode());
        }

        userDTO = userConverter.toDTO(userEntity);
        userDTO.setRoles(roles);
        return userDTO;
    }

    @Override
    public void delete(long[] ids) {
        for (long id: ids) {
            userRepository.delete(id);
        }
    }
}
