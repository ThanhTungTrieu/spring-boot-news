package com.springbootweb.service;

import com.springbootweb.dto.NewsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewsService {

    NewsDTO save(NewsDTO newsDTO);
    void delete(long[] ids);
    List<NewsDTO> findAll();
    List<NewsDTO> findAll(Pageable pageable);
    int totalItem();
}
