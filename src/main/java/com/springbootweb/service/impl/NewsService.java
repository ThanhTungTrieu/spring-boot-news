package com.springbootweb.service.impl;

import com.springbootweb.converter.NewsConverter;
import com.springbootweb.dto.NewsDTO;
import com.springbootweb.entity.CategoryEntity;
import com.springbootweb.entity.NewsEntity;
import com.springbootweb.repository.CategoryRepository;
import com.springbootweb.repository.NewsRepository;
import com.springbootweb.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsConverter newsConverter;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public NewsDTO save(NewsDTO newsDTO) {
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newsDTO.getCategoryCode());
        NewsEntity newsEntity = new NewsEntity();
        if (newsDTO.getId() != null) {
            NewsEntity oldNews = newsRepository.findOne(newsDTO.getId());
            newsEntity = newsConverter.toEntity(oldNews, newsDTO);
        } else {
            newsEntity = newsConverter.toEntity(newsDTO);
        }
        newsEntity.setCategory(categoryEntity);
        return newsConverter.toDTO(newsRepository.save(newsEntity));
    }
}
