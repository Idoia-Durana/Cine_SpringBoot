package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.component.PeliculaComp;
import com.example.demo.component.UsuarioComp;
import com.example.demo.models.entity.Pelicula;
import com.example.demo.models.entity.Usuario;
import com.example.demo.models.service.IPeliculaService;
import com.example.demo.models.service.IUsuarioService;
import com.example.demo.models.service.PeticionGetExternalmpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/peli")
public class PeliculaController {
	
	@Autowired
	private Gson gson;
	@Autowired
	private PeticionGetExternalmpl peticion;
	
	@Autowired 
	private IUsuarioService serviceUsuario;
	@Autowired 
	private IPeliculaService servicePelicula;
	
	@Autowired 
	private PeliculaComp peli;
	
	@Autowired 
	private UsuarioComp usu;
	
	
	
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String crearFormulario(Usuario usu,Model model) {
		Usuario usuario = usu;
		model.addAttribute("pelicula", peli);
		model.addAttribute("usuario", usuario);
		return "verPeliUser"; 

	}
	
	@RequestMapping(value = "/verpeli", method = RequestMethod.GET)
	public ModelAndView buscarOMDB(@RequestParam(value = "Title") String titulo) throws IOException {
		String busqueda="http://www.omdbapi.com/?apikey=dcdf1c79";
		if (titulo.contains(" ")) {
            titulo = titulo.replace(' ', '+');
        }
		busqueda+="&t=" + titulo;
		ModelAndView mav= new ModelAndView("verPeliUser");
		Usuario usuario = new Usuario();
		usuario.copia(usu);
		String texto= peticion.sendGET(busqueda);
		System.out.println(texto);
		//Una vez obtengo la respuesta en JSON lo transformo en un objeto de tipo MOVIE
		//GSON es una librería para transformar Json a objetos y al revés.
		//Para utilizarla hay que pasarle el texto(json) y el tipo de objeto al que queremos transformarlo
		
	    Pelicula pelicula = gson.fromJson(texto, Pelicula.class);
	    
	    //Hago la traduccion entre peliculacomp y pelicula
	    peli.copia(pelicula);

	  	mav.addObject("usuario", usu);
	  	mav.addObject("pelicula", peli);
	  	
		return mav;
	}
	
	@RequestMapping(value = "/guardarPeliUsuario", method = RequestMethod.GET)
	public String guardarUsuario(Model model) {
		
		String siguientePantalla;
		
		Usuario usuario= new Usuario();
		usuario.copia(usu);
		Usuario usu1= serviceUsuario.findOne(usuario.getMail());
		if(usu1==null) {
			siguientePantalla="redirect:../user/registro";
		}else {
			
			Pelicula p = new Pelicula();
			p.copia(peli);
		    servicePelicula.save(p);
		    
			usu1.aniadirPelicula(p);
			serviceUsuario.save(usu1);
			siguientePantalla="redirect:buscar";
		}
		//return siguientePantalla;
		

		model.addAttribute("pelicula", peli);
		model.addAttribute("usuario", usu);
		return siguientePantalla;
	}
	
	@RequestMapping(value = "/listarPelisUsuario", method = RequestMethod.GET)
	public String listarPelisUsuario(Model model) {
		
		String siguientePantalla;
		
		
		Usuario usuario= new Usuario();
		usuario.copia(usu);
		Usuario usu1= serviceUsuario.findOne(usuario.getMail());
		if(usu1==null) {
			siguientePantalla="redirect:../user/registro";
		}else {
			List<Pelicula> peliculas = usu1.getPeliculasFav();
			
			Pelicula peli= new Pelicula();
			model.addAttribute("peli",peli);
			model.addAttribute("peliculas", peliculas);
			model.addAttribute("usuario", usu1);
			siguientePantalla="listaPelis";
			
		}
		return siguientePantalla;

	}
	
	@RequestMapping(value = "/verPelisUsuario", method = RequestMethod.GET)
	public String verPelisUsuario(Pelicula peli1, Model model) throws IOException {
		
		System.out.println("Titulo: "+peli1.getTitle());
		
		String titulo= peli1.getTitle();
		String busqueda="http://www.omdbapi.com/?apikey=dcdf1c79";
		if (titulo.contains(" ")) {
            titulo = titulo.replace(' ', '+');
        }
		busqueda+="&t=" + titulo;

		String texto= peticion.sendGET(busqueda);
	    Pelicula pelicula = gson.fromJson(texto, Pelicula.class);
	    peli.copia(pelicula);

	    
	    titulo= peli1.getTitle();
		String busqueda2="http://www.omdbapi.com/?apikey=dcdf1c79";
		if (titulo.contains(" ")) {
            titulo = titulo.replace(' ', '+');
        }
		busqueda2+="&t=" + titulo+"&plot=full";

		String texto2= peticion.sendGET(busqueda2);
		
		JsonObject jobj = new Gson().fromJson(texto2, JsonObject.class);
		String plot = jobj.get("Plot").getAsString();
		String imdbRating = jobj.get("imdbRating").getAsString();
	    
		model.addAttribute("imdbRating", imdbRating);
	  	model.addAttribute("peli", peli);
	  	model.addAttribute("plot", plot);
		return "datosPeli";

	}

}
