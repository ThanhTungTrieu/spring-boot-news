package com.springbootweb.service.impl;

import com.springbootweb.api.input.NewsInput;
import com.springbootweb.converter.NewsConverter;
import com.springbootweb.dto.NewsDTO;
import com.springbootweb.entity.CategoryEntity;
import com.springbootweb.entity.NewsEntity;
import com.springbootweb.repository.CategoryRepository;
import com.springbootweb.repository.NewsRepository;
import com.springbootweb.service.INewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springbootweb.utils.FileUploadUtil;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class NewsService implements INewsService {


    @Autowired
    private NewsConverter newsConverter;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public NewsDTO save(NewsInput newsInput) {
        NewsDTO newsDTO = modelMapper.map(newsInput, NewsDTO.class);
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newsDTO.getCategoryCode());
        NewsEntity newsEntity = newsConverter.toEntity(newsDTO);
        newsEntity.setCategory(categoryEntity);

        //write file
        byte[] base64Thumbnail = Base64.getDecoder().decode(newsInput.getThumbnail().getBase64());
        String thumbnailDirectory = "/thumbnail/" + newsInput.getThumbnail().getName();
        fileUploadUtil.writeOrUpdateFile(base64Thumbnail, thumbnailDirectory);

        String returnedThumbnail = fileUploadUtil.root + thumbnailDirectory;
        newsEntity.setThumbnail(returnedThumbnail);
        return newsConverter.toDTO(newsRepository.save(newsEntity));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id: ids) {
            newsRepository.delete(id);
        }
    }

    @Override
    public List<NewsDTO> findAll(Pageable pageable) {
        List<NewsEntity> entities = newsRepository.findAll(pageable).getContent();
        List<NewsDTO> newsDTOs = new ArrayList<>();
        for (NewsEntity item: entities) {
            NewsDTO newsDTO = newsConverter.toDTO(item);
            newsDTO.setThumbnail(fileUploadUtil.getFileAsBase64(item.getThumbnail()));
            newsDTOs.add(newsDTO);
        }
        return newsDTOs;
    }

    @Override
    public int totalItem() {
        return (int) newsRepository.count();
    }

    @Override
    public NewsDTO findOneById(long id) {
        NewsEntity newsEntity = newsRepository.findOne(id);
        NewsDTO newsDTO = newsConverter.toDTO(newsEntity);
        newsDTO.setThumbnail(fileUploadUtil.getFileAsBase64(newsEntity.getThumbnail()));
        return newsDTO;
    }
}
