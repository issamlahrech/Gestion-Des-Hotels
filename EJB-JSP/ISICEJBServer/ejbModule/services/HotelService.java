package services;

import java.util.List;

import dao.IDaoHotel;
import dao.IDaoLocale;
import dao.IDaoRemote;
import entities.Hotel;
import entities.Ville;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
@Stateless(name = "hotelservice")
public class HotelService implements IDaoRemote<Hotel>, IDaoHotel {
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
		em.remove(em.contains(o) ? o: em.merge(o));
		return true;
	}

	@Override
	@PermitAll
	public boolean update(Hotel o) {
		try {
			
	        em.merge(o);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
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
		Query query = em.createQuery("select h from Hotel h");
		return query.getResultList();
	}
}
