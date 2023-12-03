package services;

import java.util.List;

import dao.IDaoLocale;
import dao.IDaoRemote;
import entities.Hotel;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "wafaa")
public class HotelService implements IDaoRemote<Hotel>,IDaoLocale<Hotel> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
   @PermitAll
	public Hotel create(Hotel o) {
		em.persist(o);
		return o;
	}

	@Override
	 @PermitAll
	public boolean delete(Hotel o) {
	    if (o != null) {
	        // Check if the entity is managed before trying to remove it
	        if (em.contains(o)) {
	            em.remove(o);
	            return true;
	        } else {
	            // If the entity is detached, merge it first and then remove
	            try {
	                Hotel managedEntity = em.merge(o);
	                em.remove(managedEntity);
	                return true;
	            } catch (IllegalArgumentException e) {
	                // Log or handle the exception as needed
	                e.printStackTrace();
	                return false;
	            }
	        }
	    }

	    return false;
	}




	
	@Override
	 @PermitAll
	public Hotel update(Hotel o) {
	    if (o != null) {
	      
	        Hotel updatedEntity = em.merge(o);
	        return updatedEntity;
	    }
	    return null;
	}


	@Override
	 @PermitAll
	public Hotel findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Hotel.class, id);
	}

	@Override
	 @PermitAll
	public List<Hotel> findAll() {
	    Query query = em.createQuery("select v from Hotel v");
	    List<Hotel> result = query.getResultList();

	    // Ajoutez des logs pour voir le contenu de la liste
	    for (Hotel v : result) {
	        System.out.println("Hotel ID: " + v.getId() + ", Nom: " + v.getNom());
	    }

	    return result;
	}


}