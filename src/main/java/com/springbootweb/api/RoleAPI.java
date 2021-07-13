package com.springbootweb.api;

import com.springbootweb.dto.RoleDTO;
import com.springbootweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleAPI {

    @Autowired
    private IRoleService roleService;

    @PostMapping(value = "/role")
    @Transactional
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @PutMapping(value = "/role/{id}")
    @Transactional
    public RoleDTO updateRole(@RequestBody RoleDTO updatedRoleDTO, @PathVariable("id") long id) {
        updatedRoleDTO.setId(id);
        return roleService.save(updatedRoleDTO);
    }

    @DeleteMapping(value = "/role")
    @Transactional
    public void deleteRole(@RequestBody long[] ids) {
        roleService.delete(ids);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> getTest() {
        System.out.println("test git");
        return ResponseEntity.ok("success");
    }
}
