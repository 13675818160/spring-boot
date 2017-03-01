package com.spring.net.user;

import com.alibaba.fastjson.JSON;
import com.spring.base.BizException;
import com.spring.net.BaseMethod;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujl on 2016/3/16.
 */
public class BatchUserMethod implements BaseMethod {
    private String serviceRootUrl;

    private Request request = new Request();

    public BatchUserMethod(String serviceRootUrl, List<String> useridList){
        this.serviceRootUrl = serviceRootUrl;
        request.setUseridList(useridList);
    }

    @Override
    public String getMethodUrl() {
        return serviceRootUrl + "inter/batchuser";
    }

    @Override
    public HttpRequestBase getRequest() throws BizException {
        HttpPost httpPost = new HttpPost(getMethodUrl());
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("data", JSON.toJSONString(request)));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpPost.setEntity(entity);
        return httpPost;
    }

    public static class Request extends BaseMethod.JiheRequest {
        private List<String> useridList;

        public List<String> getUseridList() {
            return useridList;
        }

        public void setUseridList(List<String> useridList) {
            this.useridList = useridList;
        }

    }

    public static class Response extends BaseMethod.JiheResponse {
        private Map<String,UserInfo> data;

        public Map<String, UserInfo> getData() {
            return data;
        }

        public void setData(Map<String, UserInfo> data) {
            this.data = data;
        }
    }
}
