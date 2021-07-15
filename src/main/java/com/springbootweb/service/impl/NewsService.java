package com.springbootweb.service.impl;

import com.springbootweb.converter.NewsConverter;
import com.springbootweb.dto.NewsDTO;
import com.springbootweb.entity.CategoryEntity;
import com.springbootweb.entity.NewsEntity;
import com.springbootweb.repository.CategoryRepository;
import com.springbootweb.repository.NewsRepository;
import com.springbootweb.service.INewsService;
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

    @Override
    @Transactional
    public NewsDTO save(NewsDTO newsDTO) {
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newsDTO.getCategoryCode());
        NewsEntity newsEntity = newsConverter.toEntity(newsDTO);
        newsEntity.setCategory(categoryEntity);

        //just png file
        byte[] base64Thumbnail = Base64.getDecoder().decode(newsDTO.getThumbnail());
        String thumbnailDirectory = "/thumbnail/" + newsDTO.getTitle() + ".png";
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
}
