<%@ page import="yemu.GetPhoto" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="application/json;charset=UTF-8"  %>

<%
response.setHeader("Content-Type", "application/json;charset=UTF-8");
    try {
        String day = request.getParameter("day");
        int num = Integer.parseInt(request.getParameter("num"));
        PrintWriter printWriter = response.getWriter();
        GetPhoto getPhoto=new GetPhoto(day,num);
        printWriter.print(getPhoto.getPhoto().toString());
        printWriter.close();
    }catch (Exception e){
        response.getWriter().println("参数有误");
    }
%>
