<%--
  Created by IntelliJ IDEA.
  User: racer01
  Date: 2017.04.24.
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        @import "curriculum.css";
        @import "backgroundVid.css";
    </style>
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
            integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
            crossorigin="anonymous"></script>
    <style>
        @import "style.css";
        @import "login.css";
    </style>
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
        <a href="profile" class="indent-20 menu_item">Profile</a>
        <a href="curriculum" class="indent-20 menu_item selected_menu_item">Curriculum view</a>
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
    <ul id="sortable">
        <c:forEach items="${pages}" var="item">
            <c:if test="${item.published == false || user.role == 'mentor'}">
                <li class="card movable" data-post-id="<c:out value="${item.id}"/>">
                    <h2>
                        <c:out value="${item.title}"/>
                    </h2>
                    <hr>
                    <c:if test="${item['class'] == 'class app.pages.TextPage'}">
                        <c:out value="${item.content}"/>
                    </c:if>

                    <c:if test="${item['class'] == 'class app.pages.AssignmentPage'}">
                        <c:out value="${item.question}"/>
                        <br>
                        Maximum score: <c:out value="${item.maxScore}"/>
                    </c:if>
                    <br>
                    <c:if test="${user.role == 'mentor'}">
                        <button type="button" onclick="sendDelete(<c:out value="${item.id}"/>)">DELETE</button>
                    </c:if>

                </li>
            </c:if>
        </c:forEach>
    </ul>
    <c:if test="${user.role == 'mentor'}">
        <ul>
            <li class="card" data-post-id="addPage" onclick="location.href='cardcreator.html';">
                <div class="addpage">
                    +
                </div>
            </li>
        </ul>
    </c:if>
    <p id="console"></p>
</section>

<script type="text/javascript" src="curriculum.js"></script>

</body>
</html>
