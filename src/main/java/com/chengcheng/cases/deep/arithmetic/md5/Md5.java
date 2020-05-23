package com.chengcheng.cases.deep.arithmetic.md5;

import io.swagger.annotations.Api;

import javax.sound.midi.Soundbank;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Api("MD5加密算法 - 1")
public class Md5 {

	/*
	 * @描述: 对字符串md5加密(小写+字母)
	 * @param str  传入要加密的字符串
	 * @return  返回固定长度字符串, MD5加密后的字符串
	 */
	public static String getMD5(String str) {

		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b = md.digest();
			int i;
			StringBuilder buf = new StringBuilder("");
			for (byte value : b) {
				i = value;
				if (i < 0) i += 256;
				if (i < 16) buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 测试
	public static void main(String[] args) {
		String res = getMD5("666");
		System.out.println(res);

	}

}
