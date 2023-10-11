package com.ruoyi.exam.util;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName secretKeyCheckUtil
 * @Description TODO
 * @Author hgq
 * @Date 2022/9/21 15:53
 * @Version 1.0
 **/
public class SecretKeyCheckUtil {

    /**
     * 第三方key
     */
    private static final String APP_KEY = "sf_exam_app_key";
    /**
     * 第三方secret
     */
    private static final String APP_SECRET = "B0Xi65gD3tjA8iLNeaLl1cDE4cDb";

    /**
     * 小程序接口秘钥校验
     *
     * @param sign
     * @param timestamp
     * @return
     */
    public static boolean appCheck(String sign, String timestamp) {
        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        if (StringUtils.isEmpty(timestamp)) {
            return false;
        }
        String time = Long.toString(System.currentTimeMillis() / 1000L);
        long l = Long.parseLong(time) - Long.parseLong(timestamp);
        if (l > 20L) {
            return false;
        }
        String s = SecureUtil.md5(APP_KEY + "&" + timestamp + "&" + APP_SECRET);
        if (!sign.equals(s)) {
            return false;
        }
        return true;
    }

}
