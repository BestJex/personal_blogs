package com.chengcheng.configration;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Api("Druid监控配置 - 01")
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
		initParams = {
				@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
		}
)
public class DruidStatFilter extends WebStatFilter {
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(new WebStatFilter());
		filter.setName("druidWebStatFilter");
		filter.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		filter.addUrlPatterns("/*");
		return filter;
	}

	@Bean
	public ServletRegistrationBean getServletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		servlet.setName("druidStatViewServlet");  // DruidStatViewServlet
		servlet.addInitParameter("resetEnable", "false");
		return servlet;
	}
}  