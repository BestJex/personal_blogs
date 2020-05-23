package com.chengcheng.cases.plug.upload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Api("图片映射配置类 - 废弃方法")
//@Configuration   // 打开注解存在问题
public class WebMvcConfig extends WebMvcConfigurationSupport {

	private static final String imgPath = "file:" + FileUtil.UPLOAD_PATH + "other" + "\\";  // 图片映射路径

	@ApiOperation("路径映射")
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("\\templates\\download\\img\\**").addResourceLocations(imgPath);  // download/img/** 映射到 imgpath
		super.addResourceHandlers(registry);
	}

}
