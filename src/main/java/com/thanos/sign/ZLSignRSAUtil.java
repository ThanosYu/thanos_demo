package com.thanos.sign;

import javax.crypto.Cipher;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by Yingjie_Li on 2015/11/26.
 */
public class ZLSignRSAUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
    public static final String MD5_SIGNATURE_ALGORITHM = "MD5withRSA";

    public static String signRSA(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = ZLSignBase64Util.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return new String(ZLSignBase64Util.encodeBase64(signature.sign()));
    }

    public static String encrypt(String source, String rsaKey) throws Exception {

        byte[] data = source.getBytes();

//        byte[] keyBytes = Base64.decode(rsaKey);
        byte[] keyBytes = ZLSignBase64Util.decodeBase64(rsaKey.getBytes());


        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		/*
		 * 用私钥进行签名 RSA Cipher负责完成加密或解密工作，基于RSA
		 */
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // ENCRYPT_MODE表示为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 加密
        byte[] rsaBytes = cipher.doFinal(data);
        // Base64编码
        return new String(ZLSignBase64Util.encodeBase64(rsaBytes));
    }

    public static String encryptForBoss
            (String source, String key) throws Exception {

        byte[] data = source.getBytes();

//        byte[] keyBytes = Base64.decode(key.getBytes());
        byte[] keyBytes = ZLSignBase64Util.decodeBase64(key.getBytes());

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		/*
		 * 用私钥进行签名 RSA Cipher负责完成加密或解密工作，基于RSA
		 */
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // ENCRYPT_MODE表示为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 加密
        byte[] rsaBytes = cipher.doFinal(data);
        // Base64编码
        return URLEncoder.encode(new String(ZLSignBase64Util.encodeBase64(rsaBytes)));
    }
}
