package com.chengcheng.cases.plug.excelpoi.others;

import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Api("提示将Excel保存客户端本地位置, 不必保存在服务器端.")
@RestController
public class ResponseEntityDemo {

	@RequestMapping(value = "/tableToxls")
	public ResponseEntity<byte[]> tableToxls() {
		HSSFWorkbook test = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			test.write(out);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			httpHeaders.setContentDispositionFormData("attachement", "table.xls");
			return  new ResponseEntity<>(out.toByteArray(), httpHeaders, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
