package com.ruoyi.exam.util;

import com.ruoyi.common.utils.uuid.UUID;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataUtils {

    /**
     * 获取uuid
     * @return
     */
    public static String uuids(){
        UUID uuid = UUID.randomUUID();
        String string = uuid.toString();
        String replace = string.replace("-", "");
        return replace;
    }

    /**
     * 两数相除
     * @param v1
     * @param v2
     * @param scale 保留小数点数
     * @return
     */
    public static double division(double v1, double v2, int scale){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        double value = 0.0;
        if(0 != v1 && 0 == v2){
            value = 1.0;
        }
        if(0 != v1 && 0 != v2){
            value = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return value;
    }

    public static void appCheck(HttpServletRequest request){
        if (!SecretKeyCheckUtil.appCheck(request.getHeader("sign"), request.getHeader("timestamp"))) {
            throw new RuntimeException("md5校验失败");
        }
    }

    /**
     * 获取指定时间毫秒数
     * @param date
     * @return
     */
    public static long getTimeMillis(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeM = 0;
        try {
            timeM = sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeM;
    }

}
