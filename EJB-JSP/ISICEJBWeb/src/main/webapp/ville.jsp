<%@page import="entities.Ville"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des villes</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<head>
    <style>
        body {
            background-color: #1d2630;
        }

        table {
            background-color:white;
            width: 100%;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        
        .btn-success {
            background-color: green;
            color: white;
        }

        
        .btn-warning {
            background-color: orange;
            color: black;
        }

        

        .btn-danger {
            background-color: red;
            color: white;
        }

        
    </style>
</head>
<body>


    <form action="VilleController" method="post">
        Nom de la ville : <input type="text" name="ville" />
        <button type="submit" class="btn btn-success">Add</button>
    </form>

    <c:if test="${not empty villes}">
        
        <table border="1">
            <thead>
                <tr>
                    
                    <th>Nom</th>
                    <th> Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${villes}" var="v">
                    <tr>
                        <td>${v.nom}</td>
                        <td>
                        <form action="VilleController" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                            <form action="VilleController" method="post">
                                <input type="text" name="ville" value="${v.nom}" />
                                <input type="hidden" name="action" value="update" />
                                <button type="submit" class="btn btn-warning">Edit</button>
                            </form>
                            
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

</body>
</html>
