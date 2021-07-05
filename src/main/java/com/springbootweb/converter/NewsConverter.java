package com.springbootweb.converter;

import com.springbootweb.dto.NewsDTO;
import com.springbootweb.entity.NewsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {

    @Autowired
    private ModelMapper modelMapper;

    public NewsDTO toDTO(NewsEntity newsEntity) {
        NewsDTO result = modelMapper.map(newsEntity, NewsDTO.class);
        result.setCategoryCode(newsEntity.getCategory().getCode());
        return result;
    }

    public NewsEntity toEntity(NewsDTO newsDTO) {
        NewsEntity result = modelMapper.map(newsDTO, NewsEntity.class);
        return result;
    }

}
