package com.m.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class EncodeUtil {
    private char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public String md5Encode(String salt,String text) throws Exception{
        //创建消息摘要对象
        MessageDigest md = MessageDigest.getInstance("MD5");
        //将盐数据传入消息摘要对象
        md.update(salt.getBytes());
        //将口令的数据传给消息摘要对象
        md.update(text.getBytes("UTF-8"));
        //生成输入口令的消息摘要
        byte[] digest = md.digest();
        return byteToHexString(digest);
    }

    /**
     * 将指定byte数组转换成16进制字符串
     * @return
     */
    private String byteToHexString(byte[] md) {
        int j = md.length;
        char str[] = new char[j * 2];  //java中byte用二进制表示占用8位，而我们知道16进制的每个字符需要用4位二进制位来表示，所以我们就可以把每个byte转换成两个相应的16进制字符
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
}
