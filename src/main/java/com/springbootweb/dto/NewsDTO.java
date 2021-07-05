package com.springbootweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDTO extends AbstractDTO<NewsDTO> {

    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;
    private String categoryCode;

}
