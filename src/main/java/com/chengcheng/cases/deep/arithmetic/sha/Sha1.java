package com.chengcheng.cases.deep.arithmetic.sha;

import io.swagger.annotations.Api;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Api("使用SHA1算法对字符串进行加密")
public class Sha1 {

	public static String sha1Encrypt(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA-1");
			mdTemp.update(str.getBytes(StandardCharsets.UTF_8));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;

			for (byte byte0 : md) {
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}

			return new String(buf);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}
