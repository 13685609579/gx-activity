package com.ruoyi.exam.util;

import com.ruoyi.common.utils.uuid.UUID;

import java.math.BigDecimal;

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

}
