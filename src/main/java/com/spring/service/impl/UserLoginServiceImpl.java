package com.spring.service.impl;

import com.spring.mapper.UserLoginStatusMapper;
import com.spring.pojo.ReqParameter;
import com.spring.pojo.UserLoginStatus;
import com.spring.base.BizException;
import com.spring.service.UserLoginService;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhujl on 2016/3/10.
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserLoginStatusMapper userLoginStatusMapper;

    @Override
    public Boolean userIsLogin(ReqParameter reqParameter) throws BizException{
        if(TextUtils.isBlank(reqParameter.getUserid()) || TextUtils.isBlank(reqParameter.getUuid())){
            throw new BizException(BizException.CODE_LACK_PARM, "【userid,uuid】为必须参数！");
        }

        UserLoginStatus userLoginStatus = userLoginStatusMapper.selectByPrimaryKey(reqParameter.getUuid());
        if(null == userLoginStatus || null == userLoginStatus.getUid() || !userLoginStatus.getUid().equals(reqParameter.getUserid()))
            throw new BizException(BizException.CODE_NO_LONIN,"【userid未在uuid登录】！");

        return true;

    }
}
