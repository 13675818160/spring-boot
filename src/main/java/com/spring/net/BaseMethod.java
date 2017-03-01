package com.spring.net;

import com.spring.base.BizException;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.Serializable;

/**
 * Created by maobg on 2015/12/29.
 */
public interface BaseMethod {

    public String getMethodUrl();
    public HttpRequestBase getRequest() throws BizException;

    public static abstract class JiheRequest implements Serializable {

    }

    public static abstract class JiheResponse implements Serializable {
        private String sc = "0";
        private String errorMsg;
        private Integer errcode = 0;
        private String errmsg;
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getErrcode() {
            return errcode;
        }

        public void setErrcode(Integer errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public String getSc() {
            return sc;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

    }
}

