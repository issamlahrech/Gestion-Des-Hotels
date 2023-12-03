package controllers;

import java.io.IOException;
import java.util.List;

import dao.IDaoHotel;
import dao.IDaoLocale;
import dao.IDaoVille;
import entities.Ville;
import entities.Hotel;

import jakarta.ejbh.ejbh;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HotelController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ejbh
    private IDaoHotel hotelDao;

    @ejbh
    private IDaoVille villeDao;

    public HotelController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Hotel> hotelList = hotelDao.findAll();
        List<Ville> villeList = villeDao.findAll();
        request.setAttribute("hotels", hotelList);
        request.setAttribute("villes", villeList);
        request.getRequestDispatcher("/hotel.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            int villeId = Integer.parseInt(request.getParameter("ville"));
            Ville ville = villeDao.findById(villeId);
            hotelDao.create(new Hotel(nom, adresse, telephone, ville));

            response.sendRedirect(request.getContextPath() + "/HotelController");

        } else if ("delete".equals(action)) {
            int hotelId = Integer.parseInt(request.getParameter("Id"));
            Hotel hotelToDelete = hotelDao.findById(hotelId);

            if (hotelToDelete != null) {
                boolean deleted = hotelDao.delete(hotelToDelete);

                if (deleted) {
                    System.out.println("Hôtel supprimé avec succès");
                } else {
                    System.out.println("La suppression de l'hôtel a échoué");
                }
            } else {
                System.out.println("Hôtel non trouvé avec l'ID : " + hotelId);
            }

            response.sendRedirect(request.getContextPath() + "/HotelController");

        } else if ("update".equals(action)) {
            String hotelId = request.getParameter("id");
            Hotel hotelToUpdate = hotelDao.findById(Integer.parseInt(hotelId));

            if (hotelToUpdate != null) {
                String updatedNom = request.getParameter("modifiedNom");
                String updatedAdresse = request.getParameter("modifiedAdresse");
                String updatedTelephone = request.getParameter("modifiedTelephone");
                String updatedVilleId = request.getParameter("modifiedVille");

                if (updatedNom != null && !updatedNom.isEmpty() &&
                        updatedAdresse != null && !updatedAdresse.isEmpty() &&
                        updatedTelephone != null && !updatedTelephone.isEmpty() &&
                        updatedVilleId != null && !updatedVilleId.isEmpty()) {

                    hotelToUpdate.setNom(updatedNom);
                    hotelToUpdate.setAdresse(updatedAdresse);
                    hotelToUpdate.setTelephone(updatedTelephone);

                    Ville updatedVille = villeDao.findById(Integer.parseInt(updatedVilleId));
                    hotelToUpdate.setVille(updatedVille);

                    hotelDao.update(hotelToUpdate);
                }
            }

            response.sendRedirect("HotelController");

            List<Ville> villeList = villeDao.findAll();
            request.setAttribute("villes", villeList);

            request.getRequestDispatcher("/hotel.jsp").forward(request, response);
        }
    }
}
