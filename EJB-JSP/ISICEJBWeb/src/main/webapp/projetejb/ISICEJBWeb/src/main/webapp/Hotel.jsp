<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Hôtels</title>
    <style>
        body {
            background-color: #f2f2f2;
            color: #333;
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1, h2 {
            color: #0066cc;
            text-align: center;
        }

        form {
            margin-bottom: 20px;
            background-color: #0066cc;
            padding: 15px;
            border-radius: 10px;
            color: white;
            text-align: center;
            width: 50%;
            margin: 0 auto;
        }

        form label {
            display: block;
            margin: 10px 0;
            text-align: left;
        }

        form input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        form input[type="submit"] {
            background-color: #004080;
            border: none;
            color: white;
            padding: 15px;
            border-radius: 5px;
            cursor: pointer;
            width: auto;
        }

        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #0066cc;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .update-form {
            display: flex;
            align-items: center;
            
        }

        .update-form input {
            margin-right: 10px;
        }
    </style>
</head>
<body>

    <!-- Titre -->
    <h1>Gestion des Hôtels</h1>

    <!-- Formulaire pour ajouter un nouvel hôtel -->
    <form action="HotelController" method="post">
        <input type="hidden" name="action" value="create">
        <label>Nom :</label>
        <input type="text" name="nom" required>
        <label>Adresse :</label>
        <input type="text" name="adresse" required>
        <label>Téléphone :</label>
        <input type="text" name="telephone" required>
        <input type="submit" value="Ajouter un Hôtel">
    </form>

    <!-- Liste des Hôtels -->
    <h2>Liste des Hôtels</h2>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Adresse</th>
                <th>Téléphone</th>
                <!-- Ajoutez d'autres colonnes au besoin -->
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="hotel" items="${Hotels}">
                <tr>
                    <td>${hotel.nom}</td>
                    <td>${hotel.adresse}</td>
                    <td>${hotel.telephone}</td>
                    <!-- Ajoutez d'autres colonnes au besoin -->
                    <td>
                        <!-- Formulaire pour mettre à jour l'hôtel -->
                        <form class="update-form" action="HotelController" method="post">
                            <input type="hidden" name="action" value="modifier">
                            <input type="hidden" name="updateId" value="${hotel.id}">
                            <input type="text" name="updatedNom" value="${hotel.nom}" required>
                            <input type="text" name="updatedAdresse" value="${hotel.adresse}" required>
                            <input type="text" name="updatedTelephone" value="${hotel.telephone}" required>
                            <input type="submit" value="Modifier">
                        </form>
                        <!-- Formulaire pour supprimer l'hôtel -->
                        <form action="HotelController" method="post">
                            <input type="hidden" name="action" value="supprimer">
                            <input type="hidden" name="deleteId" value="${hotel.id}">
                            <input type="submit" value="Supprimer">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
