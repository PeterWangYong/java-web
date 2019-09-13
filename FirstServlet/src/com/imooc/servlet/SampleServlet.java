package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet{
	
	// service是请求处理的核心方法，无论是get或者post都会被service()方法处理
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求方法
		String methodName = request.getMethod();
		// 获取单个请求参数
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		// 获取多个同名参数值
		String[] specs = request.getParameterValues("spec");
		// 向浏览器输出的数据流
		PrintWriter out = response.getWriter();
		out.println("<h1>method:" + methodName + "</h1>");
		out.println("<h1>name:" + name + "</h1>");
		out.println("<h1>mobile:" + mobile + "</h1>");
		for (String spec : specs) {
			out.println("<h2>spec:" + spec + "</h2>");
		}
		out.println("<a href='http://www.baidu.com'>Baidu</a>");
	}
}
