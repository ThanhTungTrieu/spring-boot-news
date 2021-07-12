package com.springbootweb.api;

import com.springbootweb.dto.UserDTO;
import com.springbootweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAPI {

    @Autowired
    private IUserService userService;

//    @GetMapping(value = "/user/{username}")
//    public UserDTO getUserDetails(@PathVariable("username") String username) {
//        UserDTO result = userService.findByUsername(username);
//        result.setPassword(null);
//        return result;
//    }

    @PostMapping(value = "/user")
    @Transactional
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping(value = "/user/{id}")
    @Transactional
    public UserDTO updateUser(@RequestParam UserDTO updatedUserDTO, @PathVariable("id") long id) {
        updatedUserDTO.setId(id);
        return userService.save(updatedUserDTO);
    }

    @DeleteMapping(value = "/user")
    @Transactional
    public void deleteUser(@RequestBody long[] ids) {
        userService.delete(ids);
    }
}
