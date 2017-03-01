package com.spring.net.user;

/**
 * Created by zhujl on 2016/3/16.
 */
public class UserInfo {
    private String nickname;
    private String realname;
    private String headimgurl;
    private Integer memberFlag;
    private String memberCode;
    private MobileAccount mobileAccount;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(Integer memberFlag) {
        this.memberFlag = memberFlag;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public MobileAccount getMobileAccount() {
        return mobileAccount;
    }

    public void setMobileAccount(MobileAccount mobileAccount) {
        this.mobileAccount = mobileAccount;
    }

    public class MobileAccount{
        private String accountName;
        private Integer accountType;

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public Integer getAccountType() {
            return accountType;
        }

        public void setAccountType(Integer accountType) {
            this.accountType = accountType;
        }
    }
}
