package com.herong.demo.controller;

import com.herong.demo.controller.req.LoginReq;
import com.herong.demo.controller.resp.BaseResponse;
import com.herong.demo.domain.User;
import com.herong.demo.domain.query.UserQuery;
import com.herong.demo.exception.ServiceCodeException;
import com.herong.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户模块接口
 * @author herong22384
 * @date 2020/3/22 14:21
 */
@RestController
@RequestMapping("/user")
public class UserAction {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public BaseResponse login(@RequestBody LoginReq req){
        BaseResponse response = new BaseResponse();
        try{
            if (req.getPassword() == null || req.getUserAccount()==null){
                throw new ServiceCodeException(2001,"账号和密码不能为空");
            }
            User user = userService.getUserByUserAccount(req.getUserAccount());
            if (user == null){
                throw new ServiceCodeException(2001,"用户不存在");
            }else {
                if (user.getPassword().equals(req.getPassword())){
                    response.setResult(user);
                }else {
                    throw new ServiceCodeException(2001,"密码错误");
                }
            }
        }catch (ServiceCodeException e){
            response.setErrorNo(e.getErrorCode());
            response.setErrorInfo(e.getErrorDesc());
        }catch (Exception e){
            response.setErrorNo(1000);
            response.setErrorInfo(e.getMessage());
        }
        return response;
    }

    @RequestMapping("/registry")
    public BaseResponse registry(@RequestBody User req){
        BaseResponse response = new BaseResponse();
        try{
            if (req.getUserCode()==null){
                throw new ServiceCodeException(2001,"账号不能为空");
            }
            if (req.getPassword()==null){
                throw new ServiceCodeException(2001,"登录密码不能为空");
            }
            if (req.getUserName()==null){
                throw new ServiceCodeException(2001,"姓名不能为空");
            }
            req.setUserType("T");//教师
            userService.insert(req);
        }catch (ServiceCodeException e){
            response.setErrorNo(e.getErrorCode());
            response.setErrorInfo(e.getErrorDesc());
        }catch (Exception e){
            response.setErrorNo(1000);
            response.setErrorInfo(e.getMessage());
        }
        return response;
    }

    @RequestMapping("/stu_registry")
    public BaseResponse stuRegistry(@RequestBody User req){
        BaseResponse response = new BaseResponse();
        try{
            if (req.getUserCode()==null){
                throw new ServiceCodeException(2001,"学号不能为空");
            }
            if (req.getPassword()==null){
                throw new ServiceCodeException(2001,"登录密码不能为空");
            }
            if (req.getUserName()==null){
                throw new ServiceCodeException(2001,"姓名不能为空");
            }
            req.setUserType("S");//学生
            userService.insert(req);
        }catch (ServiceCodeException e){
            response.setErrorNo(e.getErrorCode());
            response.setErrorInfo(e.getErrorDesc());
        }catch (Exception e){
            response.setErrorNo(1000);
            response.setErrorInfo(e.getMessage());
        }
        return response;
    }

    @RequestMapping("/get")
    public BaseResponse get(String userAccount){
        BaseResponse response = new BaseResponse();
        try{
            if (userAccount==null){
                throw new ServiceCodeException(2001,"账号不能为空");
            }
            User user = userService.getUserByUserAccount(userAccount);
            response.setResult(user);
        }catch (ServiceCodeException e){
            response.setErrorNo(e.getErrorCode());
            response.setErrorInfo(e.getErrorDesc());
        }catch (Exception e){
            response.setErrorNo(1000);
            response.setErrorInfo(e.getMessage());
        }
        return response;
    }

    @RequestMapping("/edit")
    public BaseResponse edit(@RequestBody User req){
        BaseResponse response = new BaseResponse();
        try{
            if (req.getUserCode()==null){
                throw new ServiceCodeException(2001,"学号不能为空");
            }
            if (req.getPassword()==null){
                throw new ServiceCodeException(2001,"登录密码不能为空");
            }
            if (req.getUserName()==null){
                throw new ServiceCodeException(2001,"姓名不能为空");
            }
            userService.update(req);
        }catch (ServiceCodeException e){
            response.setErrorNo(e.getErrorCode());
            response.setErrorInfo(e.getErrorDesc());
        }catch (Exception e){
            response.setErrorNo(1000);
            response.setErrorInfo(e.getMessage());
        }
        return response;
    }

    @RequestMapping("/delete")
    public BaseResponse delete(@RequestBody User req){
        BaseResponse response = new BaseResponse();
        try{
            if (req.getId()==null){
                throw new ServiceCodeException(2001,"id不能为空");
            }
            userService.delete(req.getId());
        }catch (ServiceCodeException e){
            response.setErrorNo(e.getErrorCode());
            response.setErrorInfo(e.getErrorDesc());
        }catch (Exception e){
            response.setErrorNo(1000);
            response.setErrorInfo(e.getMessage());
        }
        return response;
    }

    @RequestMapping("/queryByPage")
    public BaseResponse queryByPage(@RequestBody UserQuery query){
        BaseResponse response = new BaseResponse();
        try{
            userService.querByPage(query);
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

    @RequestMapping("/queryList")
    public BaseResponse queryList(@RequestBody UserQuery query){
        BaseResponse response = new BaseResponse();
        try{
            response.setResult(userService.queryUserList(query));
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
