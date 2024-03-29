package com.yc.votesys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.votesys.util.StringUtil;

/**
 * 编码集过滤器
 * 源辰信息
 * @author navy
 * @2019年4月17日
 */
public class CharacterEncodingFilter implements Filter {
	private String encoding = "utf-8";
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		String temp = arg0.getInitParameter("encoding");
		if ( !StringUtil.isNull(temp)) {
			encoding = temp; 
		}
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		arg2.doFilter(request, response); // 调用下一个过滤器过滤
	}

	@Override
	public void destroy() {
		
	}
	
}
