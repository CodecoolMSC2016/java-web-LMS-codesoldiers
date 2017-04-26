<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="app.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String user = ((User) request.getSession().getAttribute("user")).getUsername();
    String email = ((User) request.getSession().getAttribute("user")).getEmail();
    String role = ((User) request.getSession().getAttribute("user")).getRole();
%>
<html>
<head>
    <title>Home</title>
    <style>
        @import "style.css";
        @import "backgroundVid.css";
        @import "card.css";
    </style>
    <c:if test="${messageFromServlet != null}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <% String message = (String)request.getAttribute("messageFromServlet");%>
            <script>var msg = "<%=message%>";
            alert(msg);
            </script>
        </div>
    </c:if>
</head>
<body>
<video  id="bgvid" playsinline autoplay muted loop>
    <!-- WCAG general accessibility recommendation is that media such as background video play through only once. Loop turned on for the purposes of illustration; if removed, the end of the video will fade in the same way created by pressing the "Pause" button  -->
    <source src="images/moytains%20(loop).mp4" type="video/webm">
    <source src="images/moytains%20(loop).mp4" type="video/mp4">
</video>
<nav id="navbar">
    <h1 class="indent-30" id="menu_title">Menu</h1>
    <div id="menu">
        <a href="profile" class="indent-20 menu_item selected_menu_item">Profile</a>
        <a href="curriculum" class="indent-20 menu_item">Curriculum view</a>
        <a href="userlist" class="indent-20 menu_item">Users</a>
        <form id='logoutform' action="logout" method="get">
            <input type="submit" value="Logout"
                   name="Logout" id="logout" class="indent-20 menu_item emptybutton">
        </form>
    </div>
    <div class="indent-20" id="company_name">
        <h5>created by</h5>
        <h4>CodeSoldiers</h4>
        <h5>- 2017 -</h5>
    </div>
</nav>

<section id="content">
    <div class="center">
        <form id="profileform" class="inside" method="post">
            <h1 class="formtext bigtitle">Profile</h1>
            <input type="text" class="titletextarea" name="user" placeholder="Username" value="<%=user%>">
            <input type="text" class="titletextarea" name="email" placeholder="<%=email%>" disabled>
            <input type="text" class="titletextarea" name="role" placeholder="<%=role%>" disabled>
            <br>
            <p class="formtext">Change password:</p>
            <input type="password" class="titletextarea" name="currpass" placeholder="Current password">
            <input type="password" class="titletextarea" name="newpass" placeholder="New password">
            <script>
                var postProfile = function () {
                    var profileform = document.getElementById("profileform");
                    profileform.submit();
                };
            </script>
            <p align="center" onclick="postProfile()">
                <a>Update profile</a>
            </p>
        </form>
    </div>
</section>

</body>
</html>

