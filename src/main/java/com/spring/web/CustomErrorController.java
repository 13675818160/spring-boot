package com.spring.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maobg on 2015/7/7.
 */
@Controller
public class CustomErrorController implements ErrorController {
    private static Log logger = LogFactory.getLog(CustomErrorController.class);

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String,Object> handleError(){
        logger.debug("handleError");
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("sc",404);
        return resultMap;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}