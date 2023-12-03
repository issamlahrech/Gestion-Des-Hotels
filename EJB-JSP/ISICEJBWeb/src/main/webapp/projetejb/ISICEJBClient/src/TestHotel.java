import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import dao.IDaoRemote;
import entities.Hotel;

public class TestHotel {

    public static IDaoRemote<Hotel> lookUpEmployeRemote() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8084");
        final Context context = new InitialContext(jndiProperties);

        return (IDaoRemote<Hotel>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/wafaa!dao.IDaoRemote");
    }

    public static void main(String[] args) {
        try {
            IDaoRemote<Hotel> dao = lookUpEmployeRemote();
//            dao.create(new Hotel("Ibis", "Adresse1", "08976535"));
//            dao.create(new Hotel("HayatRiginssi", "Adresse2", "0987654353"));
//            dao.create(new Hotel("Hotel1", "Adresse3", "0987654321"));

            // Supprimez le commentaire pour activer la suppression
            // dao.delete(dao.findById(1));

            // Affichez toutes les Hotels
            for (Hotel v : dao.findAll()) {
                System.out.println(v.getId() + " - " + v.getNom());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            IDaoRemote<Hotel> dao = lookUpEmployeRemote();

            // Cr�ez une instance de Hotel avec un ID existant
            Hotel hotelToUpdate = new Hotel();
            hotelToUpdate.setId(6); // Remplacez 1 par l'ID r�el de l'entit� que vous souhaitez mettre � jour
            hotelToUpdate.setNom("modifier");

            // Appelez la m�thode update
            Hotel updatedHotel = dao.update(hotelToUpdate);

            // Affichez le r�sultat
            if (updatedHotel != null) {
                System.out.println("Hotel mise � jour avec succ�s : ID = " + updatedHotel.getId() + ", Nom = " + updatedHotel.getNom());
            } else {
                System.out.println("La mise � jour de la Hotel a �chou�.");
            }

            // Affichez toutes les Hotels apr�s la mise � jour
            for (Hotel v : dao.findAll()) {
                System.out.println(v.getId() + " - " + v.getNom());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
