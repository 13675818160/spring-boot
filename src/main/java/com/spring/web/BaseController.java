package com.spring.web;

import com.spring.base.BizException;
import com.spring.constants.ApplicationConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maobg on 2015/7/14.
 */
public class BaseController {
    private static Log logger = LogFactory.getLog(BaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> ExceptionHandler(Exception ex) {
        logger.error("ExceptionHandler", ex);
        Map<String,Object> resultMap = new HashMap<String,Object>();

        if(ex instanceof BizException){
            BizException bizException = (BizException) ex;
            String errorMsg = "";
            if(logger.isDebugEnabled())
                errorMsg = bizException.getMessage();
            resultMap.put(ApplicationConstants.TAG_SC, bizException.getCode());
            resultMap.put(ApplicationConstants.TAG_SC_ERRORMSG,errorMsg);
//            resultMap.put(ApplicationConstants.TAG_SC_MSG,errorMsg);
        }
        else
        {
            StringBuffer buf = new StringBuffer();
            if(logger.isDebugEnabled()) {
                buf.append("<");
                buf.append(ex.toString());
                buf.append(">");
                StringWriter sw = new StringWriter(1024);
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                pw.close();
                buf.append(sw.toString());
            }
            else{
                buf.append(ex.toString());
            }

            resultMap.put(ApplicationConstants.TAG_SC, "-99999");
            resultMap.put(ApplicationConstants.TAG_SC_ERRORMSG, buf.toString());
        }
        return resultMap;
    }
}
