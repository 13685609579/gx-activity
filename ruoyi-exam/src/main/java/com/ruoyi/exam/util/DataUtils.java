package com.ruoyi.exam.util;

import com.ruoyi.common.utils.uuid.UUID;

public class DataUtils {

    public static String uuids(){
        UUID uuid = UUID.randomUUID();
        String string = uuid.toString();
        String replace = string.replace("-", "");
        return replace;
    }

}
