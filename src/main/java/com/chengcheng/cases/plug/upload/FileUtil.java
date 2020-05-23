package com.chengcheng.cases.plug.upload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api("文件上传工具类")
public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	public static final String UPLOAD_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\templates\\download\\";  // 文件上传/图片 - 根目录

	@ApiOperation("图片存储")
	public static String saveImg(MultipartFile file) {

		String saveName = getFileSuffix(file.getOriginalFilename());
		String resultType = judgeType(saveName);
		String path = FileUtil.UPLOAD_PATH + resultType;
		logger.info("图片路径：{}, 图片名称：{}", path, saveName);
		try {
			File targetFile = new File(path);  // 保存文件图片
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(new File(path + "\\" + saveName));  // 创建新文件
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("图片保存异常：{}" + e.getMessage());
			return null;
		}
		String filePath = resultType;  // 相对路径

		return filePath + "\\" + saveName;
	}

	@ApiOperation("返回截取的文件后缀")
	private static String getFileSuffix(String path) {
		return getFileSuffix(path, "2");
	}

	@ApiOperation("获取文件名称或后缀(最后一个\".\"之后内容)")
	private static String getFileSuffix(String path, String type) {  // type 1名称 2后缀
		if (StringUtils.isNotEmpty(path) && path.indexOf(".") > 0) {
			String name = path.substring(0, path.lastIndexOf("."));  // 文件名称
			String suffix = path.substring(path.lastIndexOf(".") + 1);  // 文件后缀
			return name + '.' + suffix;
		} else {
			return null;
		}
	}

	@ApiOperation("判断文件类型")
	private static String judgeType(String saveName) {
		String suffix = saveName.substring(saveName.lastIndexOf(".") + 1);  // 文件后缀
		switch (suffix) {
			case "txt":
				return "txt";
			case "jpg":
				return "img";
			default:
				return "other";
		}
	}

}
