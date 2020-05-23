package com.chengcheng.configration;

import com.alibaba.druid.support.http.StatViewServlet;
import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;

@Api("Druid监控配置 - 02")
/*@WebServlet(urlPatterns = "/druid/*",
		initParams = {
				//@WebInitParam(name="allow",value="192.168.16.110,127.0.0.1"),
				//@WebInitParam(name="deny",value="192.168.16.111"),
				@WebInitParam(name = "loginUsername", value = "admin"),
				@WebInitParam(name = "loginPassword", value = "123"),
				@WebInitParam(name = "resetEnable", value = "false")
		})*/
@Configuration
public class DruidStatViewServlet extends StatViewServlet {
//	private static final long serialVersionUID = 1L;

	@Bean
	public ServletRegistrationBean druidServlet() {

		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//		servletRegistrationBean.addInitParameter("allow", "192.168.16.110, 127.0.0.1");  // IP白名单 (没有配置或者为空，则允许所有访问)
//		servletRegistrationBean.addInitParameter("deny", "127.0.0.1");  // IP黑名单 (存在共同时，deny优先于allow)
		servletRegistrationBean.addInitParameter("loginUsername", "root");
		servletRegistrationBean.addInitParameter("loginPassword", "root");
		servletRegistrationBean.addInitParameter("resetEnable", "true");  // 禁用HTML页面上的“Reset All”功能

		return servletRegistrationBean;

	}

}  