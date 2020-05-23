package com.chengcheng.cases.plug.upload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api("上传文件测试类")
@RestController
public class ImgUploadControllerTest {

	@ApiOperation("图片上传")
	@PostMapping(value = "/imgUpload")
	public String uploadImg(@RequestParam("image") MultipartFile img) {
		try {
			String path = FileUtil.saveImg(img);  // 图片上传调用工具类 - 保存图片
			return path;
		} catch (Exception e) {
			return "上传图片失败";
		}
	}

}
