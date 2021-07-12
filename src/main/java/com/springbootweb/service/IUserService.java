package com.springbootweb.service;

import com.springbootweb.dto.UserDTO;

public interface IUserService {

    UserDTO save(UserDTO userDTO);
    UserDTO findByUsernameAndPassword(String username, String password);
    void delete(long[] ids);
}
