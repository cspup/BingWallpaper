<%@ page import="yemu.GetPhoto" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>必应壁纸接口</title>
</head>
<body>
<%
    try {
        String day = request.getParameter("day");
        int num = Integer.parseInt(request.getParameter("num"));
        PrintWriter printWriter = response.getWriter();
        GetPhoto getPhoto=new GetPhoto(day,num);
        printWriter.println(getPhoto.getPhoto());
    }catch (Exception e){
        response.getWriter().println("参数有误");
    }

    %>
</body>
</html>
