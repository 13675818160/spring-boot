package com.spring.web;

import com.alibaba.fastjson.JSON;
import com.spring.pojo.ReqParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

/**
 * Created by maobg on 2015/7/23.
 */
public class ParameterInterceptor implements HandlerInterceptor {

    private static Log logger = LogFactory.getLog(ParameterInterceptor.class);

    ThreadLocal threadLocal = new ThreadLocal();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.debug("preHandle");
        RequestFacade.setParameters(null);
        ReqParameter parameterInfo = null;
        if(null != request.getParameter("data")) {
            parameterInfo = JSON.parseObject(request.getParameter("data"), ReqParameter.class);
            parameterInfo.setData(request.getParameter("data"));
        }
        else
        {
            parameterInfo = new ReqParameter();
        }

        Enumeration<String> header =   request.getHeaderNames();
        while (header.hasMoreElements()){
            String headername = header.nextElement();
            logger.debug(headername+":"+request.getHeader(headername));
        }

        parameterInfo.setClientrealip(null != request.getHeader("remoteip") ? request.getHeader("remoteip") : (null != request.getHeader("X-Real-IP") ? request.getHeader("X-Real-IP") : request.getRemoteAddr()));
        parameterInfo.setAction(request.getServletPath());
        parameterInfo.setQueryString(request.getQueryString());
        parameterInfo.setUseragent(request.getHeader("User-Agent"));
        parameterInfo.setLocation(null != request.getHeader("location") ? request.getHeader("location") : request.getParameter("location"));
        parameterInfo.setApiversion(null != request.getHeader("apiversion") ? request.getHeader("apiversion") : request.getParameter("apiversion"));
        parameterInfo.setChannel(null != request.getHeader("channel") ? request.getHeader("channel") : request.getParameter("channel"));
        parameterInfo.setSign(null != request.getHeader("sign") ? request.getHeader("sign") : request.getParameter("sign"));
        parameterInfo.setTimestamp(null != request.getHeader("timestamp") ? request.getHeader("timestamp") : request.getParameter("timestamp"));
        parameterInfo.setUuid(null != parameterInfo.getUuid() ? parameterInfo.getUuid() : (null != request.getHeader("uuid") ? request.getHeader("uuid") : request.getParameter("uuid")));
        parameterInfo.setUserid(null != parameterInfo.getUserid() ? parameterInfo.getUserid() : (null != request.getHeader("userid") ? request.getHeader("userid") : request.getParameter("userid")));
        parameterInfo.setBmsToken(null != request.getHeader("bmsToken") ? request.getHeader("bmsToken") : request.getParameter("bmsToken"));
        if(null != request.getCookies()){
            Cookie[]  cookies=request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("uuid") && null != cookie.getValue() && null ==parameterInfo.getUuid() && !parameterInfo.getAction().startsWith("/member/bms")){
                    parameterInfo.setUuid(cookie.getValue());
                }
                else if(cookie.getName().equals("userid") && null != cookie.getValue() && null ==parameterInfo.getUserid() && !parameterInfo.getAction().startsWith("/member/bms")) {
                    parameterInfo.setUserid(cookie.getValue());
                }
                else if(cookie.getName().equals("bmsToken") && null != cookie.getValue() && null ==parameterInfo.getBmsToken()){
                    parameterInfo.setBmsToken(cookie.getValue());
                }
            }
        }
        logger.info(parameterInfo);

        RequestFacade.setParameters(parameterInfo);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
