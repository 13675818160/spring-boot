package com.spring.utils;

import java.util.regex.Pattern;

/**
 * Created by maobg on 2015/9/10.
 */
public class ValidUtil {
    public static boolean isMobile(String mobile,String REGEX_MOBILE) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static boolean isMatch(String matchstr,String REGEX){
        return Pattern.matches(REGEX,matchstr);
    }
}
