package com.example.demo.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.IUsuarioDao;
import com.example.demo.models.entity.Usuario;

@Service
public class UsuarioServicelmpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usu) {
		usuDao.save(usu);
		
	}


	@Override
	@Transactional
	public Usuario findOne(String email) {
		return usuDao.findOne(email);
	}

	@Override
	public void delete(String mail) {
		usuDao.delete(mail);
		
	}

}
