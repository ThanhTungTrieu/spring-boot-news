package com.springbootweb.api;

import com.springbootweb.api.input.NewsInput;
import com.springbootweb.api.output.NewsOutput;
import com.springbootweb.dto.NewsDTO;
import com.springbootweb.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsAPI {

    @Autowired
    private INewsService newsService;

    @GetMapping(value = "/news")
    public NewsOutput getPageableNews(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "maxItemInOnePage", defaultValue = "1000") int maxItemInOnePage) {
        NewsOutput newsOutput = new NewsOutput();
        newsOutput.setPage(page);
        newsOutput.setTotalPage((int) Math.ceil((double) newsService.totalItem() / maxItemInOnePage));
        Pageable pageable = new PageRequest(page - 1, maxItemInOnePage);
        newsOutput.setListResult(newsService.findAll(pageable));
        return newsOutput;
    }

    @GetMapping(value = "/news/{id}")
    public NewsOutput getOneNews(@PathVariable("id") long id) {
        NewsOutput newsOutput = new NewsOutput();
        newsOutput.setPage(1);
        newsOutput.setTotalPage(1);
        List<NewsDTO> results = new ArrayList<>();
        results.add(newsService.findOneById(id));
        newsOutput.setListResult(results);
        return newsOutput;
    }

    @PostMapping(value = "/news")
    @Transactional
    public NewsDTO createNews(@RequestBody NewsInput newsInput) {
        return newsService.save(newsInput);
    }

    @PutMapping(value = "/news/{id}")
    @Transactional
    public NewsDTO updateNews(@RequestBody NewsInput updatedNewsInput, @PathVariable("id") long id) {
        updatedNewsInput.setId(id);
        return newsService.save(updatedNewsInput);
    }

    @DeleteMapping(value = "/news")
    @Transactional
    public void deleteNews(@RequestBody long[] ids) {
        newsService.delete(ids);
    }
}
