<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Users</title>
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
    <h2 class="indent-30" id="menu_title">Menu</h2>
    <div id="menu">
        <a href="#"><h3 class="indent-20 menu_item selected_menu_item">Home</h3></a>
        <a href="userlist"><h3 class="indent-20 menu_item">Users</h3></a>
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
    <!--ul id="sortable">
        <li class="card" data-post-id="0">Post 1
            <hr></hr>
        </li>
        <li class="card" data-post-id="1">Post 2
            <hr></hr>
        </li>
        <li class="card" data-post-id="2">Post 3
            <hr></hr>
        </li>
        <li class="card" data-post-id="3">Post 4
            <hr></hr>
        </li>
        <li class="card" data-post-id="4">Post 5
            <hr></hr>
        </li>
        <li class="card" data-post-id="5">Post 6
            <hr></hr>
        </li>
        <li class="card" data-post-id="6">Post 7
            <hr></hr>
        </li>
    </ul>
    <script type="text/javascript"></script>
    <script type="text/javascript" src="curriculum.js">
    </script>
    <p id="console"></p-->


    <ul>
    <c:forEach items="${userlist}" var="item">
            <li class="card">
                ${item[0]}
                <hr>
                ${item[1]}, ${item[2]}
            </li>
    </c:forEach>
    </ul>
</section>
</body>
</html>
