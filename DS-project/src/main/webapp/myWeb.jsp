<%@ page import="com.sample.jsp.bean.CallGoogle" %>
<%@ page import="com.sample.jsp.bean.WebNode" %>
<%@ page import="com.sample.jsp.bean.WebTree" %>
<%@ page import="com.sample.jsp.bean.WebPage" %>
<%@ page language="java"  import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
 <meta charset="utf-8">
    <title>登陸介面</title>
    <style>
        html {
            background-color: #B5DEF2;
        }
        
        .wrapper {
            margin: 140px 140px 70px auto;
            width: 884px;
        }
        
        .loginBox {
            background-color: #F0F4F6;
            /*上divcolor*/
            border: 1px solid #BfD6E1;
            border-radius: 5px;
            color: #444;
            font: 14px 'Microsoft YaHei', '微軟雅黑';
            margin: 0 auto;
            width: 388px
        }
        
        .loginBox .loginBoxCenter {
            border-bottom: 1px solid #DDE0E8;
            padding: 24px;
        }
        
        .loginBox .loginBoxCenter p {
            margin-bottom: 10px
        }
        
        .loginBox .loginBoxButtons {
            /*background-color: #F0F4F6;*/
            /*下divcolor*/
            border-top: 0px solid #FFF;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
            line-height: 28px;
            overflow: hidden;
            padding: 20px 24px;
            vertical-align: center;
            filter: alpha(Opacity=80);
            -moz-opacity: 0.5;
            opacity: 0.5;
        }
        
        .loginBox .loginInput {
            border: 1px solid #D2D9dC;
            border-radius: 2px;
            color: #444;
            font: 12px 'Microsoft YaHei', '微軟雅黑';
            padding: 8px 14px;
            margin-bottom: 8px;
            width: 310px;
        }
        
        .loginBox .loginInput:FOCUS {
            border: 1px solid #B7D4EA;
            box-shadow: 0 0 8px #B7D4EA;
        }
        
        .loginBox .loginBtn {
            background-image: -moz-linear-gradient(to bottom, blue, #85CFEE);
            border: 1px solid #98CCE7;
            border-radius: 20px;
            box-shadow: inset rgba(255, 255, 255, 0.6) 0 1px 1px, rgba(0, 0, 0, 0.1) 0 1px 1px;
            color: #444;
            /*登入*/
            cursor: pointer;
            float: right;
            font: bold 13px Arial;
            padding: 10px 50px;
        }
        
        .loginBox .loginBtn:HOVER {
            background-image: -moz-linear-gradient(to top, blue, #85CFEE);
        }
        
        .loginBox a.forgetLink {
            color: #ABABAB;
            cursor: pointer;
            float: right;
            font: 11px/20px Arial;
            text-decoration: none;
            vertical-align: middle;
            /*忘記密碼*/
        }
        
        .loginBox a.forgetLink:HOVER {
            color: #000000;
            text-decoration: none;
            /*忘記密碼*/
        }
        
        .loginBox input#remember {
            vertical-align: middle;
        }
        
        .loginBox label[for="remember"] {
            font: 11px Arial;
        }
    </style>
</head>
<body>
<div class="wrapper">
        <form action="myWeb.jsp" method="get">
            <div class="loginBox">
                <div class="loginBoxCenter">
                    <p><label for="username">搜尋內容</label></p>
                    <%
                    String keyword = null;
                    ArrayList<WebNode> console;
                    %>
                    <p><input type="text" id="keyword" name="keyword" class="loginInput" autofocus="autofocus" required="required" autocomplete="off" placeholder="請輸入關鍵字"  /></p>
                    <jsp:useBean id="call" class="com.sample.jsp.bean.CallGoogle" scope="page"></jsp:useBean>
	                <jsp:setProperty name="call" property="keyword" />
                    
                    
                </div>
                <div class="loginBoxButtons">


                    <button type="submit" class="loginBtn">搜尋</button>
                    <div> 以下是搜尋結果</div>
                    <%
                    String str = request.getParameter("keyword");
                                        out.println(str);
                    %>
                    <%
                    CallGoogle front = new CallGoogle();
                                        front.setKeyword(str);
                                        if(front.getKeyword()!=null){
                                        front.run();
                                        console = front.getTree().getConsole();
                                        for(int i=0;i<console.size();i++){
                                        	out.println("<br>");
                                        	%>
                                        	<a href='<%=console.get(i).webPage.url%>'> <%=console.get(i).webPage.name%> </a>
                                        	<%
                                        }
                                        }
                                        
                    %>
  					
                </div>
            </div>
        </form>
    </div>
</body>
</html>