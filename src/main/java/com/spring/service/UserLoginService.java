package com.spring.service;

import com.spring.base.BizException;
import com.spring.pojo.ReqParameter;

/**
 * Created by zhujl on 2016/3/10.
 */
public interface UserLoginService {

    /**
     * 调用user-server判断用户是否登录
     * @param reqParameter
     * @return
     * @throws BizException
     */
    Boolean userIsLogin(ReqParameter reqParameter) throws BizException;
}
