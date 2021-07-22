package com.springbootweb.service;

import com.springbootweb.api.input.NewsInput;
import com.springbootweb.dto.NewsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewsService {

    NewsDTO save(NewsInput newsInput);
    void delete(long[] ids);
    List<NewsDTO> findAll(Pageable pageable);
    int totalItem();
    NewsDTO findOneById(long id);
}
