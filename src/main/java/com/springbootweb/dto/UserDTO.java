package com.springbootweb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO extends AbstractDTO<UserDTO> {

    private String username;
    private String password;
    private String fullname;
    private String status;
    private List<String> roles = new ArrayList<>();
}
