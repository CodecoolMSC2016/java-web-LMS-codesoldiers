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
        @import "profile.css";
    </style>
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <script src="profile.js"></script>
    <c:if test="${messageFromServlet != null}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <% String message = (String) request.getAttribute("messageFromServlet");%>
            <script>var msg = "<%=message%>";
            alert(msg);
            window.location= "profile";
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

<section id="content" class="container">
    <div class="card">
        <div class="profile">
            <form id="profileform" method="post">
                <h1 class="aligncenter bigtitle">Profile</h1>
                <hr>
                <p class="aligncenter">User infos:</p>
                <table>
                    <tr>
                        <td align="right">Username:</td>
                        <td><input type="text" name="user" placeholder="Username" value="<%=user%>"></td>
                    </tr>
                    <tr>
                        <td align="right">E-mail:</td>
                        <td><input type="text" name="email" placeholder="Email" disabled="" value="<%=email%>"></td>
                    </tr>
                    <tr>
                        <td align="right">Role:</td>
                        <td><input type="text" name="role" placeholder="Role" disabled="" value="<%=role%>"></td>
                    </tr>
                </table>
                <br>
                <p class="aligncenter">Change password:</p>
                <input type="password" name="newpass" placeholder="New password">
                <br>
                <p class="aligncenter">Enter your password for changes:</p>
                <input type="password" name="currpass" placeholder="Current password">
                <div align="center">
                    <a onclick="document.getElementById('profileform').submit();">Update profile</a>
                </div>

            </form>
            <div>

                <a onclick="postDelete()">
                    Delete profile
                </a>
            </div>
        </div>
    </div>
</section>

</body>
</html>

