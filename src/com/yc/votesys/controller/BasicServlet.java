package com.yc.votesys.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BasicServlet extends HttpServlet{
	private static final long serialVersionUID = 6438234709716102903L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	public void send(HttpServletResponse response, int result) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	
	public void send(HttpServletResponse response, String str) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(str);
		out.flush();
		out.close();
	}
	
	public void send(HttpServletResponse response, Object obj) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		out.print(gson.toJson(obj));
		out.flush();
		out.close();
	}
}
