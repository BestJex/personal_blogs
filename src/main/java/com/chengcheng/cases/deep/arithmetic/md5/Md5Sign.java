package com.chengcheng.cases.deep.arithmetic.md5;

import com.chengcheng.cases.deep.arithmetic.md5.Md5;
import io.swagger.annotations.Api;

@Api("MD5+Sign版本号防止篡改 - 2")
public class Md5Sign {

	public static String recharge(int phone, int money, String sign) {
		String sb = "money=" + money + "&phone=" + phone;
		// 撞库, 是不是能够找出这个规律
		String md5Str = Md5.getMD5(sb);
		if (!sign.equals(md5Str)) {
			return "警告: 接口被篡改";
		}

		// 执行正常业务逻辑...
		return "充值成功";
	}

	// 模拟客户端程序
/*	public static void main(String[] args) {
		String sb = "money=12345&phone=10";
		System.out.println( Md5.getMD5(sb)); // 9775595e0e4c68b1
	}*/

	// 测试
	public static void main(String[] args) {
		String res = recharge(10, 12345, "9775595e0e4c68b1");
		System.out.println(res);
	}

}
