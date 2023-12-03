package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.IDaoLocale;
import entities.Ville;

/**
 * Servlet implementation class VilleController
 */
@WebServlet("/VilleController")
public class VilleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB(beanName = "kenza")
    private IDaoLocale<Ville> ejb;

    public VilleController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    creerVille(request, response);
                    break;
                case "supprimer":
                    supprimerVille(request, response);
                    break;
                case "modifier":
                    modifierVille(request, response);
                    break;
                default:
                    // Gérer une action inconnue
                    afficherToutesVilles(request, response);
                    break;
            }
        } else {
            // Comportement par défaut, afficher toutes les villes
            afficherToutesVilles(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Appeler la méthode appropriée pour traiter les requêtes POST
        doGet(request, response);
    }




    private void creerVille(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("ville");

        // Check if the "ville" parameter is not empty before creating a new city
        if (nom != null && !nom.isEmpty()) {
            ejb.create(new Ville(nom));
        }

        afficherToutesVilles(request, response);
    }

    private void supprimerVille(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteId = request.getParameter("deleteId");

        if (deleteId != null && !deleteId.isEmpty()) {
            try {
                int idToDelete = Integer.parseInt(deleteId);
                Ville villeToDelete = ejb.findById(idToDelete);

                if (villeToDelete != null) {
                    ejb.delete(villeToDelete);
                    System.out.println("Ville supprimée avec succès.");
                } else {
                    System.out.println("Aucune ville trouvée avec l'ID : " + idToDelete);
                }
            } catch (NumberFormatException e) {
                System.out.println("L'ID de la ville à supprimer n'est pas un nombre valide.");
            }
        } else {
            System.out.println("L'ID de la ville à supprimer est nul ou vide.");
        }

        afficherToutesVilles(request, response);
    }


    private void modifierVille(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String updateId = request.getParameter("updateId");
        String updatedNom = request.getParameter("updatedNom");

        if (updateId != null && !updateId.isEmpty() && updatedNom != null && !updatedNom.isEmpty()) {
            try {
                int idToUpdate = Integer.parseInt(updateId);
                Ville villeToUpdate = ejb.findById(idToUpdate);

                if (villeToUpdate != null) {
                    // Mettez à jour les informations de la ville
                    villeToUpdate.setNom(updatedNom);
                    
                    // Appelez la méthode update
                    ejb.update(villeToUpdate);
                    System.out.println("Ville mise à jour avec succès.");
                } else {
                    System.out.println("Aucune ville trouvée avec l'ID : " + idToUpdate);
                }
            } catch (NumberFormatException e) {
                System.out.println("L'ID de la ville à mettre à jour n'est pas un nombre valide.");
            }
        } else {
            System.out.println("ID de mise à jour ou nom de la ville à mettre à jour est nul ou vide.");
        }

        afficherToutesVilles(request, response);
    }


    private void afficherToutesVilles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("villes", ejb.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }
}
