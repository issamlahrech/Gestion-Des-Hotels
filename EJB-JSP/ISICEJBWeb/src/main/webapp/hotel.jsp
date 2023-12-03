<%@page import="entities.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des hotels</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    
    <style>
        body {
            background-color: #1d2630;
            color: #fff;
        }

        .icon-color {
            color: #007BFF;
        }

        #Update {
            display: none;
        }
    </style>
</head>
<body>

    <div class="container">
        <form action="HotelController" method="post" class="mb-4">
            <div class="form-row">
                <div class="col">
                    <input type="text" name="nom" class="form-control" placeholder="Nom">
                </div>
                <div class="col">
                    <input type="text" name="adresse" class="form-control" placeholder="Adresse">
                </div>
                <div class="col">
                    <input type="text" name="telephone" class="form-control" placeholder="Téléphone">
                </div>
                <div class="col">
                    <select name="ville" class="form-control">
                        <c:forEach items="${villes}" var="v">
                            <option value="${v.id }">${v.nom }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <input type="hidden" name="action" value="create" />
                    <button type="submit" class="btn btn-purple">Add</button>
                </div>
            </div>
        </form>

        <!-- Move the filter input and label to the top right of the page -->
        <div id="filterContainer" class="mb-4">
            <label id="filterLabel" for="villeFilter">Hotels par Ville:</label>
            <input type="text" id="villeFilter" oninput="filterTable()" class="form-control">
        </div>

        <!-- Display success or error messages -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                ${successMessage}
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">
                ${errorMessage}
            </div>
        </c:if>

        <c:if test="${not empty hotels}">
            
            <table class="table table-bordered" id="hotelsList">
                <thead class="thead-light">
                    <tr>
                        
                        <th>Nom</th>
                        <th>Adresse</th>
                        <th>Téléphone</th>
                        <th>Ville</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${hotels}" var="h">
                        <tr>
                           
                            <td>${h.nom}</td>
                            <td>${h.adresse}</td>
                            <td>${h.telephone}</td>
                            <td>${h.ville.nom}</td>
                            <td>
                                <form action="HotelController" method="post" class="mr-2">
                                    <input type="text" name="modifiedNom" value="${h.nom}" class="form-control">
                                    <input type="text" name="modifiedAdresse" value="${h.adresse}" class="form-control">
                                    <input type="text" name="modifiedTelephone" value="${h.telephone}" class="form-control">
                                    <select name="modifiedVille" class="form-control">
                                        <c:forEach items="${villes}" var="vil">
                                            <option value="${vil.id}" ${vil.id eq h.ville.id ? 'selected' : ''}>${vil.nom}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="id" value="${h.id}">
                                    <button type="submit" class="btn btn-warning">Update</button>
                                </form>

                                <form action="HotelController" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cet hôtel ?');">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="Id" value="${h.id}">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        
    </div>
</body>
</html>
