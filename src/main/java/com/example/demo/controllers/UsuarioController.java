package com.example.demo.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.component.PeliculaComp;
import com.example.demo.component.UsuarioComp;
import com.example.demo.models.entity.Usuario;
import com.example.demo.models.service.IUsuarioService;

@Controller
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	Usuario usuario;
	
	@Autowired
	private IUsuarioService usuService;
	
	@Autowired 
	private PeliculaComp peli;
	
	@Autowired 
	private UsuarioComp usu;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		Usuario usu= new Usuario();
		model.addAttribute("usu", usu);
		boolean correcto=false;
		model.addAttribute("correcto",correcto);
		return "login";
	}
	
	@RequestMapping(value = "/logueado", method = RequestMethod.GET)
	public String loguear(Usuario usuario,Model model) {
		boolean correcto=false;
		List<Usuario> usuarios = usuService.findAll();
		for (Usuario u : usuarios) {
			if(u.getMail().equals(usuario.getMail()) && u.getPass().equals(usuario.getPass())) {
				usu.copia(usuario);
				correcto=true;
			}
		}
		if(correcto) {
			model.addAttribute("usuario", usu);
			model.addAttribute("pelicula", peli);
			return "verPeliUser";
		}else {
			Usuario usu= new Usuario();
			model.addAttribute("usu", usu);
			correcto=true;
			model.addAttribute("correcto",correcto);
			return "login";
		}
		
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registro(Model model) {
		Usuario usu= new Usuario();
		model.addAttribute("usu", usu);
		return "registro";
	}
	
	@RequestMapping(value = "/registrado", method = RequestMethod.GET)
	public String registrado(Usuario usu, Model model) {	
		usuService.save(usu);
		return "redirect:login";
	}
	
}
