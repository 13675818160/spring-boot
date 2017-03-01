package com.spring.service.cache;

import com.spring.mapper.SysParameterMapper;
import com.spring.pojo.SysParameter;
import com.spring.pojo.SysParameterExample;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maobg on 2015/7/16.
 */
@Scope("singleton")
@Service
public class DictSysParameter {
    private static Log logger = LogFactory.getLog(DictSysParameter.class);

    @Autowired
    SysParameterMapper sysParameterMapper;

    private Map<String,SysParameter> parameterMap = new HashMap<String,SysParameter>();

    @PostConstruct
    public Integer init() {
        parameterMap.clear();
        List<SysParameter> list = sysParameterMapper.selectByExample(new SysParameterExample());
        for (SysParameter sysParameter:list) {
            parameterMap.put(sysParameter.getParamKey(), sysParameter);
        }
        return list.size();
    }

    public String getParameter(String key, String default_value) {
        SysParameter sysParameter = parameterMap.get(key);
        if(null == sysParameter || null == sysParameter.getParamValue() || 0 == sysParameter.getParamValue().length())
            return default_value;
        else
            return sysParameter.getParamValue();
    }

    public Integer getParameter(String key, Integer default_value) {
        SysParameter sysParameter = parameterMap.get(key);
        if(null == sysParameter || null == sysParameter.getParamValue() || 0 == sysParameter.getParamValue().length())
            return default_value;
        else
            return Integer.valueOf(sysParameter.getParamValue());
    }

    public void refresh() {
        logger.debug("reload system parameters");
        init();
    }
}
