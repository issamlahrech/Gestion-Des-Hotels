<%@ page import="entities.Ville" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="ISO-8859-1">
    <title>Simple Blue Design</title>
    <style>
        body {
            background-color: #f2f8fd; /* Light blue background color */
            font-family: Arial, sans-serif;
            color: #333; /* Dark text color */
            margin: 20px;
        }

        form {
            margin-bottom: 20px;
        }

        h1 {
            color: #007bff; /* Blue header color */
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            background-color: #ffffff; /* White background color for list items */
            margin-bottom: 10px;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Style for the delete button */
        .delete-button {
            background-color: #ff3333;
            color: #fff;
            border: none;
            padding: 8px 12px;
            margin-left: 10px;
            cursor: pointer;
            border-radius: 5px;
        }

        /* Style for the forms */
        form input[type="text"],
        form button {
            padding: 10px;
            margin: 5px;
            border: 1px solid #007bff;
            border-radius: 5px;
        }

        form button {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        /* Style for the update form */
        form#updateForm {
            margin-top: 20px;
        }

        /* Responsive design for smaller screens */
        @media (max-width: 600px) {
            li {
                padding: 10px;
            }

            form input[type="text"],
            form button {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <form action="VilleController" method="post">
        <input type="hidden" name="action" value="create" />
        Nom : <input type="text" name="ville" /> <br>
        <button type="submit">Enregistrer</button>
    </form>

    <h1>Liste des villes : </h1>
    <ul>
        <c:forEach items="${villes}" var="v">
            <li>${v.id} - ${v.nom}
                <!-- Delete button for each city -->
                <form action="VilleController" method="post" style="display: inline;">
                    <input type="hidden" name="action" value="supprimer" />
                    <input type="hidden" name="deleteId" value="${v.id}" />
                    <button type="submit" class="delete-button">Supprimer</button>
                </form>
            </li>
        </c:forEach>
    </ul>

    <!-- Update form for a city -->
    <form action="VilleController" method="post" id="updateForm">
        <input type="hidden" name="action" value="modifier" />
        <input type="text" name="updateId" placeholder="ID de la ville à mettre à jour" />
        <input type="text" name="updatedNom" placeholder="Nouveau nom de la ville" />
        <button type="submit">Modifier</button>
    </form>

</body>
</html>
