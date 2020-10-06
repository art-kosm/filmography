<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Table V01</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->  
    <link rel="icon" type="image/png" href="../../res/images/icons/favicon.ico"/>
<!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../../res/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../../res/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../../res/vendor/animate/animate.css">
<!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../../res/vendor/select2/select2.min.css">
<!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../../res/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../../res/css/util.css">
    <link rel="stylesheet" type="text/css" href="../../res/css/main.css">
<!--===============================================================================================-->
</head>
<body>
    <h2>Films</h2>
    <div class="limiter">
        <div class="container-table100">
            <div class="wrap-table100">
                <div class="table100">
                    <table>
                        <thead>
                            <tr class="table100-head">
                                <th class="column1">#</th>
                                <th class="column2">Title</th>
                                <th class="column3">Year</th>
                                <th class="column4">Genre</th>
                                <th class="column5">Watched</th>
                                <th class="column6">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="film" items="${filmsList}">
                                <tr>
                                    <td class="column1">${film.id}</td>
                                    <td class="column2">${film.title}</td>
                                    <td class="column3">${film.year}</td>
                                    <td class="column4">${film.genre}</td>
                                    <td class="column5">${film.watched}</td>
                                    <td class="column6">
                                        <a href="/edit/${film.id}">edit</a>
                                        <a href="/delete/${film.id}">delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="addButton">
                        <c:url value="/add" var="add"/>
                        <a href="${add}">Add new film</a>
                    </div>
                    <div class="pagesList">
                        page:
                        <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
                            <c:url value="/" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <a href="${url}">${i.index}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!--===============================================================================================--> 
    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
    <script src="vendor/bootstrap/js/popper.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
    <script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
    <script src="js/main.js"></script>
</body>
</html>