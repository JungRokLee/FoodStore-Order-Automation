<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="com.oreilly.servlet.MultipartRequest,com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*,java.io.*" %>
<%@ page import="java.sql.*" %>
<%@page import="java.sql.Timestamp"%>
<%@page import="Food_Package.*"%>

<%
	request.setCharacterEncoding("EUC-KR");
    String id = request.getParameter("id");
%>

<jsp:useBean id="dto" class="Food_Package.FoodDto" />
<jsp:setProperty name="dto" property="*" />

<%
 request.setCharacterEncoding("euc-kr");
 String realFolder = "";
 String filename1 = "";
 int maxSize = 1024*1024*5;
 String encType = "euc-kr";
 String savefile = "img";
 ServletContext scontext = getServletContext();
 realFolder = scontext.getRealPath(savefile);
 
 try{
  MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

  Enumeration<?> files = multi.getFileNames();
     String file1 = (String)files.nextElement();
     filename1 = multi.getFilesystemName(file1);
     String name = multi.getParameter("name");
     String priceS = multi.getParameter("price");
     int price = Integer.parseInt(priceS);
     String inform = multi.getParameter("inform");

     dto.setId(id);
     dto.setName(name);
     dto.setPrice(price);
	 dto.setInform(inform);
 } catch(Exception e) {
  e.printStackTrace();
 }
 
 String fullpath = realFolder + "\\" + filename1;

 dto.setPicture(fullpath);
 FoodDao dao = FoodDao.getInstance();
 dao.insertfood(dto);
 response.sendRedirect("menuList.jsp?id="+id);


 %>


</body>
</html>

