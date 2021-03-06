package com.pilicon.orders.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式:时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int i = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(i);
    }
}
