package controllers;

import jakarta.ejbv.ejbv;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.IDaoLocale;
import dao.IDaoVille;
import entities.Ville;

/**
 * Servlet implementation class VilleController
 */
public class VilleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ejbv
    private IDaoVille villeDao;

    public VilleController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("ville");

        villeDao.create(new Ville(nom));

        request.setAttribute("villes", villeDao.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");

        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String nom = request.getParameter("ville");
            villeDao.create(new Ville(nom));

            response.sendRedirect(request.getContextPath() + "/VilleController");
        } else if ("delete".equals(action)) {
            int villeId = Integer.parseInt(request.getParameter("id"));
            Ville villeToDelete = villeDao.findById(villeId);

            if (villeToDelete != null) {
                boolean deleted = villeDao.delete(villeToDelete);

                if (deleted) {
                    List<Ville> villeList = villeDao.findAll();
                    request.setAttribute("villes", villeList);
                    request.getRequestDispatcher("ville.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/VilleController?deleteFailed=true");
                }
            }

        } else if ("update".equals(action)) {
            String idParameter = request.getParameter("id");
            String updatedVilleName = request.getParameter("ville");

            int id = Integer.parseInt(idParameter);
            Ville vUpdate = villeDao.findById(id);

            if (vUpdate != null) {
                vUpdate.setNom(updatedVilleName);
                villeDao.update(vUpdate);
                response.sendRedirect(request.getContextPath() + "/VilleController?update");
            } else {
                System.out.println("Ville non trouvée avec l'ID : " + id);
            }
        }
    }
}

	 