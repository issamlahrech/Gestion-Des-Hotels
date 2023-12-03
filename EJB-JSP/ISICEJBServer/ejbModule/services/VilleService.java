package services;

import java.util.List;

import dao.IDaoLocale;
import dao.IDaoRemote;
import dao.IDaoVille;
import entities.Ville;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "villeservice")
public class VilleService implements IDaoRemote<Ville>, IDaoVille {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Ville create(Ville o) {
		em.persist(o);
		return o;
	}

	@Override
	@PermitAll

	public boolean delete(Ville o) {
		em.remove(em.contains(o) ? o: em.merge(o));
		return true;
	}

	@Override
	@PermitAll
	public boolean update(Ville o) {
	    Ville existingVille = em.find(Ville.class, o.getId());

	    if (existingVille != null) {
	        
	        existingVille.setNom(o.getNom());
	      
	       
	        em.merge(existingVille);
	    }

	    return true;
	}

	@Override
	@PermitAll
	public Ville findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Ville.class, id);
	}

	@Override
	@PermitAll
	public List<Ville> findAll() {
		Query query = em.createQuery("select v from Ville v");
		return query.getResultList();
	}

}
