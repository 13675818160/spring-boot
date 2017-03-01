package com.spring.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by hutiejiong on 2015/11/6 0006.
 */
public class JiheUtils {

    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     */
    private static double GetDistance(double lng1, double lat1, double lng2, double lat2)
    {

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 获取优惠的距离描述
     */
    public static String GetDistanceDesc(Double userLng,Double userLat,Double hotelLng,Double hotelLat,
                                         Double cityLng,Double cityLat,String cityName){
        String distDesc="";

        if( null == userLng  || null == userLat || (0==userLng  && 0==userLat) ){
            distDesc = "距离"+cityName+"中心"+
                    Math.round(GetDistance(hotelLng, hotelLat, cityLng, cityLat) / 1000) + "公里";
            return distDesc;
        }

        Double hotelDist=GetDistance(userLng, userLat, hotelLng, hotelLat);
        if( hotelDist < 1000) {
            distDesc = "距离" + Math.round(hotelDist) + "米";
            return distDesc;
        }
        if( hotelDist < 10*1000) {
            distDesc = "距离" + String.format("%.1f",hotelDist/1000) + "公里";
            return distDesc;
        }
        if( hotelDist > 200*1000 ) {
            distDesc = "距离"+cityName+"中心"+
                    Math.round(GetDistance(hotelLng, hotelLat, cityLng, cityLat) / 1000) + "公里";
        }
        else {
            distDesc = "距离"+Math.round(hotelDist/1000)+"公里";
        }

        return distDesc;
    }

    public static Integer priceConvertPoints(Integer amount,Integer convertRatio){
        return (int)(amount.doubleValue()/convertRatio);
    }

    public static Integer priceConvertExchangePoints(Integer amount){
        return Math.round(amount.floatValue()/10);
    }

    public static Integer amountDiscount(Integer amount,float discount){
        return Math.round(discount*amount);
    }

    public static Integer PointsConvertCash(Integer points,Integer convertRatio){
        return points*convertRatio;
    }

    /**
     * 添加千位分隔符
     */
    public static String addSeparatorInNum(Integer num){
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###.##");
        return  df.format(num);
    }

    public static String addSeparatorInNum(Float num){
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###.##");
        return  df.format(num);
    }

    public static String convertFenToYuan(Integer price){
        return new DecimalFormat("#.##").format(price.floatValue() / 100);
    }

    public static String generateOrderid(){
        Random random = new Random();
        return String.format("%02d", random.nextInt(100))+String.valueOf(System.currentTimeMillis()) + String.format("%02d", random.nextInt(100));
    }

    public static String hideBankNumber(String bankNumber){
        if (null == bankNumber || bankNumber.length() < 4 )
            return bankNumber;
        else {
            return bankNumber.substring(0,4) + "****" + bankNumber.substring(bankNumber.length()-4,bankNumber.length());
        }
    }

//    public static void main(String[] args) {
//        System.out.printf(String.valueOf((float)(100-75)/100));
//        System.out.printf(amountDiscount(70000,(100-75)/100).toString());
//    }

}
