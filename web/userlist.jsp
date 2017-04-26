<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Users</title>
    <style>
        @import "style.css";
        @import "userlist.css";
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
        <a href="curriculum" class="indent-20 menu_item">Curriculum view</a>
        <a href="userlist" class="indent-20 menu_item selected_menu_item">Users</a>
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
    <ul id="sortable">
        <li class="card" data-post-id="<c:out value="${item.id}"/>">
            <h2>
                Users
            </h2>
            <hr>
            <table>
                <th>Username</th>
                <th>E-mail address</th>
                <th>Role</th>
                <c:forEach items="${userlist}" var="item">
                    <tr>
                        <td>
                                ${item[0]}
                        </td>
                        <td>
                                ${item[1]}
                        </td>
                        <td>
                                ${item[2]}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </li>
    </ul>
</section>


</body>
</html>
