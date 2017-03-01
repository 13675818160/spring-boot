package com.spring.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by maobg on 2015/7/7.
 */
public class ReqParameter {

    /**
     * 基本参数
     */
    private String action;
    private String useragent;
    private String location;
    private String apiversion;
    private String channel;
    private String uuid;
    private String userid;
    private String sign;
    private String clientrealip;
    private String data;
    private String queryString;

    /**
     * 分页参数
     */
    private Integer pageno;
    private Integer pagecnt;

    /**
     * 通用参数
     * @return
     */

    private Integer id;
    private Integer couponId;
    private String couponCode;
    private Integer productId;
    private Integer hotelId;
    private Integer roomId;
    private Date checkin;
    private Date checkout;
    private Integer quantity;
    private Integer payType;
    private Integer payStatus;
    private Integer usageStatus;
    private String orderId;
    private List<String> selectedCoupons;
    private String mobile;
    private Integer grantType;
    private Integer grantCnt;
    private Integer amount;
    private Integer points;
    private Integer defaultSelectDiscount;

    private Integer accountId;
    private Integer accountType;
    private Integer accountItem;
    private List<Integer> accountItems;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private String accountName;
    private String accountNumber;
    private String bankName;
    private String transferType;
    private String remark;
    private Integer needBankInfo;
    private Integer needTransferInfo;
    private List<Integer> accountTypes;


    //来自bms/oms的页面请求参数
    private String bmsToken;
    private Integer bmsHotelId;
    private String bmsAccount;

    //来自内部服务器的请求参数,md5校验使用
    private String timestamp;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApiversion() {
        return apiversion;
    }

    public void setApiversion(String apiversion) {
        this.apiversion = apiversion;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getClientrealip() {
        return clientrealip;
    }

    public void setClientrealip(String clientrealip) {
        this.clientrealip = clientrealip;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getPagecnt() {
        return pagecnt;
    }

    public void setPagecnt(Integer pagecnt) {
        this.pagecnt = pagecnt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getBmsToken() {
        return bmsToken;
    }

    public void setBmsToken(String bmsToken) {
        this.bmsToken = bmsToken;
    }

    public Integer getBmsHotelId() {
        return bmsHotelId;
    }

    public void setBmsHotelId(Integer bmsHotelId) {
        this.bmsHotelId = bmsHotelId;
    }

    public String getBmsAccount() {
        return bmsAccount;
    }

    public void setBmsAccount(String bmsAccount) {
        this.bmsAccount = bmsAccount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getSelectedCoupons() {
        return selectedCoupons;
    }

    public void setSelectedCoupons(List<String> selectedCoupons) {
        this.selectedCoupons = selectedCoupons;
    }

    public Integer getUsageStatus() {
        return usageStatus;
    }

    public void setUsageStatus(Integer usageStatus) {
        this.usageStatus = usageStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGrantType() {
        return grantType;
    }

    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }

    public Integer getGrantCnt() {
        return grantCnt;
    }

    public void setGrantCnt(Integer grantCnt) {
        this.grantCnt = grantCnt;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getDefaultSelectDiscount() {
        return defaultSelectDiscount;
    }

    public void setDefaultSelectDiscount(Integer defaultSelectDiscount) {
        this.defaultSelectDiscount = defaultSelectDiscount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getAccountItem() {
        return accountItem;
    }

    public void setAccountItem(Integer accountItem) {
        this.accountItem = accountItem;
    }

    public List<Integer> getAccountItems() {
        return accountItems;
    }

    public void setAccountItems(List<Integer> accountItems) {
        this.accountItems = accountItems;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNeedBankInfo() {
        return needBankInfo;
    }

    public void setNeedBankInfo(Integer needBankInfo) {
        this.needBankInfo = needBankInfo;
    }

    public Integer getNeedTransferInfo() {
        return needTransferInfo;
    }

    public void setNeedTransferInfo(Integer needTransferInfo) {
        this.needTransferInfo = needTransferInfo;
    }

    public List<Integer> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<Integer> accountTypes) {
        this.accountTypes = accountTypes;
    }

    @Override
    public String toString() {
        return "ReqParameter{" +
                "action='" + action + '\'' +
                ", useragent='" + useragent + '\'' +
                ", location='" + location + '\'' +
                ", apiversion='" + apiversion + '\'' +
                ", channel='" + channel + '\'' +
                ", uuid='" + uuid + '\'' +
                ", userid='" + userid + '\'' +
                ", sign='" + sign + '\'' +
                ", clientrealip='" + clientrealip + '\'' +
                ", pageno=" + pageno + '\'' +
                ", pagecnt=" + pagecnt + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
