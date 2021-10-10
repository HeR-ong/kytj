package com.herong.demo.controller;

import com.github.pagehelper.util.StringUtil;
import com.herong.demo.controller.resp.BaseResponse;
import com.herong.demo.domain.PostgraduateInfo;
import com.herong.demo.domain.query.PostgraduateInfoQuery;
import com.herong.demo.exception.ServiceCodeException;
import com.herong.demo.service.PostgraduateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 历年考研信息获取相关接口
 * @author herong22384
 * @date 2020/4/19 13:00
 */
@RestController
@RequestMapping("/ky")
public class PostgraduateInfoController {

    @Autowired
    private PostgraduateInfoService infoService;

    /**
     * 历年考研数据分页查询
     * */
    @RequestMapping("/queryByPage")
    public BaseResponse queryByPage(@RequestBody PostgraduateInfoQuery query){
        BaseResponse response = new BaseResponse();
        try{
            infoService.queryByPage(query);
            response.setResult(query);
        }catch (ServiceCodeException e){
            response.setErrorNo(e.getErrorCode());
            response.setErrorInfo(e.getErrorDesc());
        }catch (Exception e){
            response.setErrorNo(1000);
            response.setErrorInfo(e.getMessage());
        }
        return response;
    }

    /**
     * 首页推荐考研数据top10接口
     * */
    @RequestMapping("/recommendTop10")
    public BaseResponse recommendTop10(@RequestBody PostgraduateInfoQuery query){
        BaseResponse response = new BaseResponse();
        try{
            response.setResult(infoService.recommendTop10(query));
        }catch (ServiceCodeException e){
            response.setErrorNo(e.getErrorCode());
            response.setErrorInfo(e.getErrorDesc());
        }catch (Exception e){
            response.setErrorNo(1000);
            response.setErrorInfo(e.getMessage());
        }
        return response;
    }
}
