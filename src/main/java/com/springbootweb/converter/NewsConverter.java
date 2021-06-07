package com.springbootweb.converter;

import com.springbootweb.dto.NewsDTO;
import com.springbootweb.entity.NewsEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {

    public NewsDTO toDTO(NewsEntity newsEntity) {
        NewsDTO result = new NewsDTO();
        result.setId(newsEntity.getId());
        result.setTitle(newsEntity.getTitle());
        result.setThumbnail(newsEntity.getThumbnail());
        result.setShortDescription(newsEntity.getShortDescription());
        result.setContent(newsEntity.getContent());
        result.setCategoryCode(newsEntity.getCategory().getCode());
        return result;
    }

    public NewsEntity toEntity(NewsDTO newsDTO) {
        NewsEntity result = new NewsEntity();
        result.setTitle(newsDTO.getTitle());
        result.setThumbnail(newsDTO.getThumbnail());
        result.setShortDescription(newsDTO.getShortDescription());
        result.setContent(newsDTO.getContent());
        return result;
    }

    public NewsEntity toEntity(NewsEntity result, NewsDTO newsDTO) {
        result.setTitle(newsDTO.getTitle());
        result.setThumbnail(newsDTO.getThumbnail());
        result.setShortDescription(newsDTO.getShortDescription());
        result.setContent(newsDTO.getContent());
        return result;
    }

}
