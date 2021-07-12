package com.springbootweb.service;

import com.springbootweb.dto.RoleDTO;

import java.util.List;

public interface IRoleService {

    List<RoleDTO> findByCode(List<String> codes);
    RoleDTO save(RoleDTO roleDTO);
    void delete(long[] ids);
}
