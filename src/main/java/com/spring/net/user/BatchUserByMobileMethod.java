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
 * Created by zhujl on 2016/6/24.
 */
public class BatchUserByMobileMethod implements BaseMethod {
    private String serviceRootUrl;

    private Request request = new Request();

    public BatchUserByMobileMethod(String serviceRootUrl, List<String> mobileList){
        this.serviceRootUrl = serviceRootUrl;
        request.setMobileList(mobileList);
    }

    @Override
    public String getMethodUrl() {
        return serviceRootUrl + "inter/batchuserbymobile";
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
        private List<String> mobileList;

        public List<String> getMobileList() {
            return mobileList;
        }

        public void setMobileList(List<String> mobileList) {
            this.mobileList = mobileList;
        }
    }

    public static class Response extends BaseMethod.JiheResponse {
        private Map<String,String> data;

        public Map<String, String> getData() {
            return data;
        }

        public void setData(Map<String, String> data) {
            this.data = data;
        }
    }
}
