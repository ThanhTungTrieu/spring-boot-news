package com.springbootweb.api;

import com.springbootweb.dto.NewsDTO;
import com.springbootweb.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsAPI {

    @Autowired
    private INewsService newsService;

    @PostMapping(value = "/news")
    @Transactional
    public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
        return newsService.save(newsDTO);
    }

    @PutMapping(value = "/news/{id}")
    @Transactional
    public NewsDTO updateNews(@RequestBody NewsDTO updatedNewsDTO, @PathVariable("id") long id) {
        updatedNewsDTO.setId(id);
        return newsService.save(updatedNewsDTO);
    }

    @DeleteMapping(value = "/news")
    @Transactional
    public void deleteNews(@RequestBody long[] ids) {
        newsService.delete(ids);
    }
}
