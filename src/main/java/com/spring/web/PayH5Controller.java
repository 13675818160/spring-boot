package com.spring.web;

import com.spring.base.BizException;
import com.spring.constants.ApplicationConstants;
import com.spring.pojo.ReqParameter;
import com.spring.base.BizException;
import com.spring.constants.ApplicationConstants;
import com.spring.pojo.ReqParameter;
import com.spring.service.UserLoginService;
import com.spring.web.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujl on 2016/6/3.
 */
@RestController
@RequestMapping(value="/pay/h5")
public class PayH5Controller extends BaseController{

    @Autowired
    UserLoginService userLoginService;

    @ResponseBody
    @RequestMapping(value = "/user/{action}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,Object> payableContent(@PathVariable(value = "action") String action) throws BizException,ParseException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ReqParameter reqParameter = RequestFacade.getParameters();
        if ("isLogin".equals(action)) {
            resultMap.put(ApplicationConstants.TAG_DATA, userLoginService.userIsLogin(reqParameter));
        }else{
            throw new BizException("404","不支持的操作");
        }
        resultMap.put(ApplicationConstants.TAG_SC, ApplicationConstants.SC_OK);
        return resultMap;
    }
}
