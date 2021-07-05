package com.springbootweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO extends AbstractDTO<CategoryDTO> {

    public String name;
    public String code;
}
