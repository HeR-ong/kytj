package com.herong.demo.service.impl;

import com.herong.demo.dao.PostgraduateInfoDao;
import com.herong.demo.domain.PostgraduateInfo;
import com.herong.demo.domain.query.PostgraduateInfoQuery;
import com.herong.demo.service.PostgraduateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author herong22384
 * @date 2020/4/19 12:57
 */
@Service
public class PostgraduateInfoServiceImpl implements PostgraduateInfoService {

    @Autowired
    private PostgraduateInfoDao postgraduateInfoDao;

    @Override
    public PostgraduateInfo selectById(Long id) {
        return postgraduateInfoDao.selectById(id);
    }

    @Override
    public void delete(Long id) {
        postgraduateInfoDao.delete(id);
    }

    @Override
    public void insert(PostgraduateInfo word) {
        postgraduateInfoDao.insert(word);
    }

    @Override
    public void update(PostgraduateInfo word) {
        postgraduateInfoDao.update(word);
    }

    @Override
    public void queryByPage(PostgraduateInfoQuery query) {
        query.startPage(true);
        List<PostgraduateInfo> postgraduateInfos = postgraduateInfoDao.queryByPage(query);
        query.compable(postgraduateInfos);
    }

    @Override
    public List<PostgraduateInfo> recommendTop10(PostgraduateInfoQuery query) {
        query.startPage(false);
        return postgraduateInfoDao.recommendTop10(query);
    }
}
