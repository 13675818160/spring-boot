package com.spring.web;

import com.spring.constants.ApplicationConstants;
import com.spring.pojo.ReqParameter;
import com.spring.base.BizException;
import com.spring.service.UserLoginService;
import com.spring.service.cache.DictSysParameter;
import com.spring.utils.ValidUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Created by zhujl on 2016/3/6.
 */
public class SecurityInterceptor  implements HandlerInterceptor {

    private static Log logger = LogFactory.getLog(SecurityInterceptor.class);

    @Autowired
    DictSysParameter dictSysParameter;

    @Autowired
    UserLoginService userLoginService;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        logger.debug("preHandle");
        ReqParameter reqParameter = RequestFacade.getParameters();

        if(reqParameter.getAction().startsWith("/pay/client"))
            checkSign(reqParameter);
        else if(reqParameter.getAction().startsWith("/pay/h5"))
            checkLogin(reqParameter);
        else if(reqParameter.getAction().startsWith("/pay/order"))
            checkJiheIp(reqParameter);
        else if(reqParameter.getAction().startsWith("/pay/inter"))
            checkJiheIp(reqParameter);

        return true;
    }


    private void checkReqTime(ReqParameter reqParameter) throws BizException{

    }

    private void checkSign(ReqParameter reqParameter) throws BizException {
        if(null == reqParameter.getData())
            throw new BizException(BizException.CODE_SIGN_ERROR,"【缺少签名data】！");

        Map<String, String> dataSignMap = new HashMap<String, String>();
        dataSignMap.put("userid", DigestUtils.md5Hex(reqParameter.getUserid()));
        dataSignMap.put("uuid",DigestUtils.md5Hex(reqParameter.getUuid()));
        dataSignMap.put("data", DigestUtils.md5Hex(reqParameter.getData()));
        dataSignMap.put("v", reqParameter.getApiversion());
        dataSignMap.put("c",reqParameter.getChannel());

        String secretMd5 = DigestUtils.md5Hex("lebusishu");

        List<String> paramNames = new ArrayList<String>(dataSignMap.size());
        paramNames.addAll(dataSignMap.keySet());
        Collections.sort(paramNames);

        StringBuffer sb = new StringBuffer();
        sb.append(secretMd5);
        for (String paramName : paramNames) {
            sb.append(paramName).append(dataSignMap.get(paramName));
        }
        sb.append(secretMd5);

        String serverSign = DigestUtils.md5Hex(sb.toString());
        logger.debug("serverSign:" + serverSign);

        if(!serverSign.equals(reqParameter.getSign()))
            throw new BizException(BizException.CODE_SIGN_ERROR,BizException.MSG_SIGN_ERROR);
    }

    private void checkLogin(ReqParameter reqParameter) throws BizException {
        userLoginService.userIsLogin(reqParameter);
    }

    private void checkJiheIp(ReqParameter reqParameter) throws BizException {
        String regxList[] = dictSysParameter.getParameter("jihe_server_iplist","").split("[|]");
        for(String regx:regxList)
        {
            if(ValidUtil.isMatch(reqParameter.getClientrealip(),regx))
                return;
        }
        throw new BizException(BizException.CODE_IPAUTH_ERROR,BizException.MSG_IPAUTH_ERROR);
    }

    private void checkMd5(ReqParameter reqParameter) throws  BizException{
        logger.debug(DigestUtils.md5Hex(reqParameter.getTimestamp() + ApplicationConstants.md5_key));
        if(null == reqParameter.getSign() || null == reqParameter.getTimestamp()
                || !reqParameter.getSign().equalsIgnoreCase(DigestUtils.md5Hex(reqParameter.getTimestamp() + ApplicationConstants.md5_key)))
            throw new BizException(BizException.CODE_SIGN_ERROR,BizException.MSG_SIGN_ERROR);
    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
