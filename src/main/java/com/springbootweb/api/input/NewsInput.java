package com.springbootweb.api.input;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsInput {

    private Long id;
    private String title;
    private ThumbnailInput thumbnail;
    private String shortDescription;
    private String content;
    private String categoryCode;

    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

}
