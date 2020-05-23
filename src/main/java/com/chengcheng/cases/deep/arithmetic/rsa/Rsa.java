package com.chengcheng.cases.deep.arithmetic.rsa;

import io.swagger.annotations.Api;

import java.util.UUID;


@Api("RSA加密 - 时间戳控制登录超时")
public class Rsa {

	public static String toLogin(String name, String passwd) {

		// 限制次数, 时间, -------  密文加一个有效期
//		passwd = RSA.priDecode(passwd);  // RSA私钥解密
		// 123456 1545897382431
		String time = passwd.substring(passwd.length() - 13);
		if (System.currentTimeMillis() - Long.parseLong(time) > 2 * 60 * 1000) {  // 两分钟内有效
			return "登录异常, 时间超时.";
		}

		boolean flag = true;  // 模拟验证数据库密码是否正确 false为不正确
		if (flag) {
			String token = UUID.randomUUID().toString();  // 模拟TOKEN
			// 此处添加TOKEN,Cookie等操作..
			return "登录成功: " + token;
		}

		return "登录失败, 密码错误等...";
	}

	// 模拟客户端
/*	public static void main(String[] args) {
		String pwd = "123456" + System.currentTimeMillis();  // 随机串
		System.out.println(pwd);  // 1234561579143371715
//		String pwd_rsa = RSA.pubEncode(pwd);  // RSA公钥加密
	}*/

	// 测试
	public static void main(String[] args) {
		String res = toLogin("123456", "1234561579143371715");
		System.out.println(res);
	}
}
