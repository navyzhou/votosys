package com.yc.votesys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.votesys.biz.IUserInfoBiz;
import com.yc.votesys.biz.impl.UserInfoBizImpl;
import com.yc.votesys.entity.UserInfo;

@WebServlet("/user")
public class UserInfoServlet extends BasicServlet{
	private static final long serialVersionUID = 1289806008158121938L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("reg".equals(op)) { // 注册
			reg(request, response);
		} else if ("login".equals(op)) { // 登录
			login(request, response);
		}
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		IUserInfoBiz userInfoBiz = new UserInfoBizImpl();
		UserInfo uf = userInfoBiz.login(new UserInfo(0, uname, pwd));
		int result = 0;
		if (uf != null) {
			HttpSession session = request.getSession();
			session.setAttribute("currentLoginUser", uf);
			result = 1;
		}
		this.send(response, result);
	}

	private void reg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		IUserInfoBiz userInfoBiz = new UserInfoBizImpl();
		int result = userInfoBiz.reg(new UserInfo(0, uname, pwd));
		this.send(response, result);
	}
}
