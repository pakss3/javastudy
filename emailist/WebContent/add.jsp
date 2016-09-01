<%@page import="kr.co.saramin.emailist.DAO.EmailListDao"%>
<%@page import="kr.co.saramin.emailist.VO.emailListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//한글처리
	request.setCharacterEncoding("utf-8");
	
    String firstName = request.getParameter("fn");
    String lastName = request.getParameter("ln");
    String email = request.getParameter("email");
    
	emailListVO vo = new emailListVO();
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
			    
	EmailListDao dao = new EmailListDao();
	dao.insert(vo);
	
	response.sendRedirect("/emailist/list.jsp");
%>