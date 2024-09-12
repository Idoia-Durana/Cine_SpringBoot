package com.example.demo.component;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.models.entity.Pelicula;
import com.example.demo.models.entity.Usuario;

@Component
@SessionScope
public class UsuarioComp {
	private static final long serialVersionUID = 1L;

	@Id
	private String mail;

	private String nombre;	

	private String apellido1;

	private String apellido2;
	
	private String pass;

	private int añoNacimiento;
	
	@ManyToMany
	private List<Pelicula> peliculasFav;
	
	
	public UsuarioComp(String nombre, String apellido1, String apellido2, String mail, int añoNacimiento) {
		super();

		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.mail = mail;
		this.añoNacimiento = añoNacimiento;
	}
	
	
	
	public UsuarioComp() {
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getAñoNacimiento() {
		return añoNacimiento;
	}

	public void setAñoNacimiento(int añoNacimiento) {
		this.añoNacimiento = añoNacimiento;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public List<Pelicula> getPeliculasFav() {
		return peliculasFav;
	}


	public void setPeliculasFav(List<Pelicula> peliculasFav) {
		this.peliculasFav = peliculasFav;
	}
	
	
	
	public void aniadirPelicula(Pelicula p) {
		this.peliculasFav.add(p);
	}
	
	@Override
	public String toString() {
		return "Usuario [email=" + mail + ", nombre=" + nombre + ", apellido=" + apellido1 + ", peliculas=" + peliculasFav+ "]";
	}
	
	public void copia(Usuario usu) {
		this.mail=usu.getMail();
		this.nombre=usu.getNombre();
		this.apellido1=usu.getApellido1();
		this.apellido2=usu.getPass();
		this.pass=usu.getPass();
		this.peliculasFav=usu.getPeliculasFav();
		this.añoNacimiento=usu.getAñoNacimiento();
	}
}
