package com.herong.demo.service;

import com.herong.demo.domain.PostgraduateInfo;
import com.herong.demo.domain.query.PostgraduateInfoQuery;

import java.util.List;

/**
 * @author herong22384
 * @date 2020/4/19 12:57
 */
public interface PostgraduateInfoService {
    PostgraduateInfo selectById(Long id);
    void delete(Long id);
    void insert(PostgraduateInfo word);
    void update(PostgraduateInfo word);
    void queryByPage(PostgraduateInfoQuery query);
    List<PostgraduateInfo> recommendTop10(PostgraduateInfoQuery query);
}
