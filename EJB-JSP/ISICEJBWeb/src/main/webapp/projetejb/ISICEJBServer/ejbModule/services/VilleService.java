package services;

import java.util.List;

import dao.IDaoLocale;
import dao.IDaoRemote;

import entities.Hotel;
import entities.Ville;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "kenza")
public class VilleService implements IDaoRemote<Ville>, IDaoLocale<Ville> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Ville create(Ville o) {
		em.persist(o);
		return o;
	}

	@Override
	public boolean delete(Ville o) {
	    if (o != null) {
	        // Check if the entity is managed before trying to remove it
	        if (em.contains(o)) {
	            em.remove(o);
	            return true;
	        } else {
	            // If the entity is detached, merge it first and then remove
	            try {
	                Ville managedEntity = em.merge(o);
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
	public Ville update(Ville o) {
	    if (o != null) {
	        // Utilisez la méthode merge pour mettre à jour l'entité détachée
	        Ville updatedEntity = em.merge(o);
	        return updatedEntity;
	    }
	    return null;
	}


	@Override
	public Ville findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Ville.class, id);
	}

	@Override
	public List<Ville> findAll() {
	    Query query = em.createQuery("select v from Ville v");
	    List<Ville> result = query.getResultList();

	    // Ajoutez des logs pour voir le contenu de la liste
	    for (Ville v : result) {
	        System.out.println("Ville ID: " + v.getId() + ", Nom: " + v.getNom());
	    }

	    return result;
	}


}