package com.springbootweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList<>();

}
