package com.springbootweb.api.output;

import com.springbootweb.dto.NewsDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NewsOutput {

    private int page;
    private int totalPage;
    private List<NewsDTO> listResult = new ArrayList<>();
}
