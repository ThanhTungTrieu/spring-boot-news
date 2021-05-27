package com.springbootweb.api;

import com.springbootweb.dto.NewsDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsAPI {

    @PostMapping(value = "/news")
    public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
        return newsDTO;
    }

    @PutMapping(value = "/news")
    public NewsDTO updateNews(@RequestBody NewsDTO newsDTO) {
        return newsDTO;
    }

    @DeleteMapping(value = "/news")
    public void deleteNews(@RequestBody long[] ids) {

    }
}
