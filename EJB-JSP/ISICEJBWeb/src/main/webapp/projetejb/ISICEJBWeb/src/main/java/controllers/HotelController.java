	package controllers;
	
	import java.io.IOException;
	
	import dao.IDaoLocale;
	
	import entities.Hotel;
	import jakarta.ejb.EJB;
	import jakarta.servlet.RequestDispatcher;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	
	
	@WebServlet("/HotelController")
	public class HotelController extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	
	    @EJB(beanName = "wafaa")
	    private IDaoLocale<Hotel> ejb;
	
	    public HotelController() {
	        super();
	    }
	
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");
	
	        if (action != null) {
	            switch (action) {
	                case "create":
	                    creerHotel(request, response);
	                    break;
	                case "supprimer":
	                    supprimerHotel(request, response);
	                    break;
	                case "modifier":
	                    modifierHotel(request, response);
	                    break;
	                default:
	                    // Gérer une action inconnue
	                    afficherToutesHotels(request, response);
	                    break;
	            }
	        } else {
	            // Comportement par défaut, afficher toutes les Hotels
	            afficherToutesHotels(request, response);
	        }
	    }
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Appeler la méthode appropriée pour traiter les requêtes POST
	        doGet(request, response);
	    }
	
	    private void creerHotel(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String nom = request.getParameter("nom");
	        String adresse = request.getParameter("adresse");
	        String telephone = request.getParameter("telephone");
	
	        // Check if the parameters are not empty before creating a new hotel
	        if (nom != null && !nom.isEmpty() && adresse != null && !adresse.isEmpty() && telephone != null
	                && !telephone.isEmpty()) {
	            ejb.create(new Hotel(nom, adresse, telephone));
	        }
	
	        afficherToutesHotels(request, response);
	    }
	
	    private void supprimerHotel(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String deleteId = request.getParameter("deleteId");
	
	        if (deleteId != null && !deleteId.isEmpty()) {
	            try {
	                int idToDelete = Integer.parseInt(deleteId);
	                Hotel hotelToDelete = ejb.findById(idToDelete);
	
	                if (hotelToDelete != null) {
	                    ejb.delete(hotelToDelete);
	                    System.out.println("Hotel supprimée avec succès.");
	                } else {
	                    System.out.println("Aucune Hotel trouvée avec l'ID : " + idToDelete);
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("L'ID de la Hotel à supprimer n'est pas un nombre valide.");
	            }
	        } else {
	            System.out.println("L'ID de la Hotel à supprimer est nul ou vide.");
	        }
	
	        afficherToutesHotels(request, response);
	    }
	
	    private void modifierHotel(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String updateId = request.getParameter("updateId");
	        String updatedNom = request.getParameter("updatedNom");
	        String updatedAdresse = request.getParameter("updatedAdresse");
	        String updatedTelephone = request.getParameter("updatedTelephone");

	        if (updateId != null && !updateId.isEmpty() && updatedNom != null && !updatedNom.isEmpty()) {
	            try {
	                int idToUpdate = Integer.parseInt(updateId);
	                Hotel hotelToUpdate = ejb.findById(idToUpdate);

	                if (hotelToUpdate != null) {
	                    // Mettez à jour les informations de l'hôtel
	                    hotelToUpdate.setNom(updatedNom);
	                    hotelToUpdate.setAdresse(updatedAdresse);
	                    hotelToUpdate.setTelephone(updatedTelephone);

	                    // Appelez la méthode update
	                    ejb.update(hotelToUpdate);
	                    System.out.println("Hôtel mise à jour avec succès.");
	                } else {
	                    System.out.println("Aucun Hôtel trouvé avec l'ID : " + idToUpdate);
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("L'ID de la Hôtel à mettre à jour n'est pas un nombre valide.");
	            }
	        } else {
	            System.out.println("ID de mise à jour ou nom de la Hôtel à mettre à jour est nul ou vide.");
	        }

	        afficherToutesHotels(request, response);
	    }


	    private void afficherToutesHotels(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.setAttribute("Hotels", ejb.findAll());
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Hotel.jsp");
	        dispatcher.forward(request, response);
	    }
	}
