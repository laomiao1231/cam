package com.m.util;

import java.util.Random;

public class RandomUtil {
    public static String Suffix() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < 4; i++) {
            stringBuffer.append(random.nextInt(10));
        }
        return stringBuffer.toString();
    }
}
