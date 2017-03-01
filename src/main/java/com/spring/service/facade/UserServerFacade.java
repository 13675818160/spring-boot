package com.spring.service.facade;

import com.spring.base.BizException;
import com.spring.constants.ApplicationConstants;
import com.spring.net.HttpPoolUtil;
import com.spring.net.user.BatchUserByMobileMethod;
import com.spring.net.user.BatchUserMethod;
import com.spring.net.user.UserInfo;
import com.spring.service.cache.DictSysParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhujl on 2016/3/16.
 */
@Scope("singleton")
@Service
public class UserServerFacade {

    @Autowired
    DictSysParameter dictSysParameter;

    @Autowired
    HttpPoolUtil httpPoolUtil;

    public Map<String,UserInfo> getUserMap(List<String> useridList) throws BizException{
        BatchUserMethod batchUserMethod = new BatchUserMethod(dictSysParameter.getParameter(ApplicationConstants.SYS_PARAM_KEY_SERVER_ROOTURL_USER,"http://127.0.0.1/user"),useridList);
        BatchUserMethod.Response response = httpPoolUtil.doRequest(batchUserMethod.getRequest(),BatchUserMethod.Response.class);
        return response.getData();
    }

    public Map<String,String> getUidMap(List<String> mobileList) throws BizException{
        BatchUserByMobileMethod batchUserMethod = new BatchUserByMobileMethod(dictSysParameter.getParameter(ApplicationConstants.SYS_PARAM_KEY_SERVER_ROOTURL_USER,"http://127.0.0.1/user"),mobileList);
        BatchUserByMobileMethod.Response response = httpPoolUtil.doRequest(batchUserMethod.getRequest(),BatchUserByMobileMethod.Response.class);
        return response.getData();
    }
}
