package com.chengcheng.cases.deep.arithmetic.base64;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.Objects;

@Api("Base64 测试程序")
public class Base64Case {

	@ApiOperation("主函数")
	public static void main(String[] args) {

		// 通过base64 将图片转换为base64编码
		String imgFile = "f:\\1.ico";  // 待处理的图片
		String imgbese = getImgStr(imgFile);  // 将图片转换成Base64编码
		System.out.println(imgbese);

		// 通过base64将base64编码还原成图片
		String imgFilePath = "f:\\zh.ico";  // 要新生成的图片位置
		generateImage(imgbese, imgFilePath);

	}

	/**
	 * 将图片转换成Base64编码
	 * @param imgFile 待处理图片
	 */
	private static String getImgStr(String imgFile) {
		//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		//读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Objects.requireNonNull(Base64.encodeBase64(data)));
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * @param imgStr      图片数据
	 * @param imgFilePath 保存图片全路径地址
	 */
	private static void generateImage(String imgStr, String imgFilePath) {
		if (imgStr == null) //图像数据为空
			return;

		try {
			//Base64解码
			byte[] b = Base64.decodeBase64(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据
					b[i] += 256;
				}
			}
			//生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception ignored) {
			System.out.println("异常");
		}
	}
}
