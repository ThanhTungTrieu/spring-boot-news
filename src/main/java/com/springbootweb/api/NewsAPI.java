package com.springbootweb.api;

import com.springbootweb.api.output.NewsOutput;
import com.springbootweb.dto.NewsDTO;
import com.springbootweb.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsAPI {

    @Autowired
    private INewsService newsService;

    @GetMapping(value = "/news")
    public NewsOutput getPageableNews(@RequestParam int page, @RequestParam int maxItemInOnePage) {
        NewsOutput newsOutput = new NewsOutput();
        newsOutput.setPage(page);
        newsOutput.setTotalPage((int) Math.ceil((double) newsService.totalItem() / maxItemInOnePage));
        Pageable pageable = new PageRequest(page - 1, maxItemInOnePage);
        newsOutput.setListResult(newsService.findAll(pageable));
        return newsOutput;
    }

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
