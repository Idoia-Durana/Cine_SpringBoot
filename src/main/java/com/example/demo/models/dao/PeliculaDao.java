package com.example.demo.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Pelicula;
import com.example.demo.models.entity.Usuario;

@Repository
public class PeliculaDao implements IPeliculaDao{

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	@Override
	public List<Pelicula> findAll() {
		return em.createQuery("select c from Pelicula c").getResultList();
	}

	@Override
	@Transactional
	public void save(Pelicula peli) {
		if (peli.getImdbId() != null) {
			em.merge(peli);
		} else {
			em.persist(peli);
		}
	}

	
	@Override
	@Transactional(readOnly = true)
	public Pelicula findOne(Long id) {
		return em.find(Pelicula.class, id);
	}

	
	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
