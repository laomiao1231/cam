package com.m.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class EncodeUtil {
    private char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public String md5Encode(String salt,String text) throws Exception{
        //������ϢժҪ����
        MessageDigest md = MessageDigest.getInstance("MD5");
        //�������ݴ�����ϢժҪ����
        md.update(salt.getBytes());
        //����������ݴ�����ϢժҪ����
        md.update(text.getBytes("UTF-8"));
        //��������������ϢժҪ
        byte[] digest = md.digest();
        return byteToHexString(digest);
    }

    /**
     * ��ָ��byte����ת����16�����ַ���
     * @return
     */
    private String byteToHexString(byte[] md) {
        int j = md.length;
        char str[] = new char[j * 2];  //java��byte�ö����Ʊ�ʾռ��8λ��������֪��16���Ƶ�ÿ���ַ���Ҫ��4λ������λ����ʾ���������ǾͿ��԰�ÿ��byteת����������Ӧ��16�����ַ�
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
}
