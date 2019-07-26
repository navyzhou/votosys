package com.yc.votesys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.votesys.biz.ITopicBiz;
import com.yc.votesys.biz.impl.TopicBizImpl;
import com.yc.votesys.entity.UserInfo;
import com.yc.votesys.util.StringUtil;

@WebServlet("/topic")
public class TopicServlet extends BasicServlet {
	private static final long serialVersionUID = -2686179703924765642L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("add".equals(op)) {
			add(request, response);
		} else if ("findAll".equals(op)) {
			findAll(request, response);
		} else if ("findByTno".equals(op)) {
			findByTno(request, response);
		} else if ("vote".equals(op)) {
			vote(request, response);
		}
	}

	private void vote(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("currentLoginUser");
	
		if (obj != null) {
			UserInfo uf = (UserInfo) obj;
			ITopicBiz topicBiz = new TopicBizImpl();
			
			String tid = request.getParameter("tid");
			String usids = request.getParameter("usids");
			String tnos = request.getParameter("tnos");
			
			if (StringUtil.isNull(usids)) {
				usids = String.valueOf(uf.getUsid());
			} else {
				usids += "&" + String.valueOf(uf.getUsid());
			}
			this.send(response, topicBiz.vote(tid, usids, tnos));
		} else {
			this.send(response, -1);
		}
	}

	private void findByTno(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITopicBiz topicBiz = new TopicBizImpl();
		this.send(response, topicBiz.findByTno(request.getParameter("tno")));
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITopicBiz topicBiz = new TopicBizImpl();
		this.send(response, topicBiz.findAll());
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		String options = request.getParameter("options");
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("currentLoginUser");
	
		if (obj != null) {
			UserInfo uf = (UserInfo) obj;
			ITopicBiz topicBiz = new TopicBizImpl();
			int result = topicBiz.add(title, type, options, uf.getUsid());
			this.send(response, result);
		} else {
			this.send(response, -1);
		}
	}
}
