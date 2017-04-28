<%--
  Created by IntelliJ IDEA.
  User: david_szilagyi
  Date: 2017.04.25.
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <style>
        @import "backgroundVid.css";
        @import "style.css";
        @import "login.css";
    </style>
    <c:if test="${messageFromServlet != null}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <% String message = (String) request.getAttribute("messageFromServlet");%>
            <script>var msg = "<%=message%>";
            alert(msg);
            window.location= "login";
            </script>
        </div>
    </c:if>
</head>
<body>
<video id="bgvid" playsinline autoplay muted loop>
    <!-- WCAG general accessibility recommendation is that media such as background video play through only once. Loop turned on for the purposes of illustration; if removed, the end of the video will fade in the same way created by pressing the "Pause" button  -->
    <source src="images/moytains%20(loop).mp4" type="video/webm">
    <source src="images/moytains%20(loop).mp4" type="video/mp4">
</video>

<nav id="navbar">
    <h1 class="indent-30" id="menu_title">Menu</h1>
    <div id="menu">
        <a href="login" class="indent-20 menu_item selected_menu_item">Login</a>
    </div>
    <div class="indent-20" id="company_name">
        <h5>created by</h5>
        <h4>CodeSoldiers</h4>
        <h5>- 2017 -</h5>
    </div>
</nav>


<section id="content">
    <div class="center">
        <div class="horizontal">
            <form class="inside left" action="register" method="post">
                <h2>Register</h2>
                <input class="linput" type="text" name="user" placeholder="Name">
                <br>
                <input class="linput" type="email" name="email" placeholder="Email address">
                <br>
                <input class="linput" type="password" name="pass" placeholder="Password">
                <br>
                <input type="radio" name="role" value="student" checked>Student</input>
                <input type="radio" name="role" value="mentor">Mentor</input>
                <br> <br>
                <input class="lbutton" type="submit" value="Register">
            </form>

            <form class="inside left sideleft" action="login" method="post">
                <h2>Login</h2>
                <input class="linput" type="email" name="email" placeholder="Email address">
                <br>
                <input class="linput" type="password" name="pass" placeholder="Password">
                <br> <br>
                <input class="lbutton" type="submit" value="Login">
            </form>
        </div>
    </div>
</section>
<div id="videoloop">
</div>
</body>
</html>
