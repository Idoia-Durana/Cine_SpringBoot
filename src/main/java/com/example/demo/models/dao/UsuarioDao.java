package com.example.demo.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Usuario;

@Repository
public class UsuarioDao implements IUsuarioDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() {
		return em.createQuery("select c from Usuario c").getResultList();
	}

	@Override
	@Transactional
	public void save(Usuario usu) {
		if (usu.getMail() != null) {
			em.merge(usu);
		} else {
			em.persist(usu);
		}
	}

	
	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(String mail) {
		return em.find(Usuario.class, mail);
	}

	
	@Override
	@Transactional
	public void delete(String mail) {
		em.remove(findOne(mail));
	}
}
