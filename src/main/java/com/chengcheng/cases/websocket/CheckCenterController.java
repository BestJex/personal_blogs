package com.chengcheng.cases.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {

	/**
	 * 页面请求
	 * 测试地址: http://localhost/checkcenter/socket/20
	 * 通过后台访问地址: localhost/webSocket
	 */
	@GetMapping("/socket/{cid}")
	public ModelAndView socket(@PathVariable String cid) {
		ModelAndView mav = new ModelAndView("/socket");
		mav.addObject("cid", cid);
		return mav;
	}

	/**
	 * 推送数据接口
	 * 测试地址: http://localhost/checkcenter/socket/push/20?message=cccccc
	 */
	@ResponseBody
	@RequestMapping("/socket/push/{cid}")
	public String pushToWeb(@PathVariable String cid, String message) {
		try {
			WebSocketServer.sendInfo(message, cid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cid + ":" + message;
	}
}

