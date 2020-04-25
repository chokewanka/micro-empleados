package pe.com.chokewanka.springboot.micro.empleados.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pe.com.chokewanka.springboot.micro.empleados.model.Proyecto;
import pe.com.chokewanka.springboot.micro.empleados.service.ProyectoService;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

	@Autowired
	public ProyectoService proyectoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Proyecto> listar(@RequestParam Long idLocal){
		return proyectoService.findAll(idLocal);
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public List<Proyecto> filtrar( @RequestParam Map<String,String> filters ){
		return proyectoService.filter(filters);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Proyecto ver(@PathVariable Long id){
		return proyectoService.findById(id);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Long crear(@RequestBody Proyecto proyecto) {
		return proyectoService.create(proyecto);
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void editar(@PathVariable Long id, @RequestBody Proyecto proyecto) {
		proyectoService.edit(id, proyecto);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		proyectoService.delete(id);
	}
	
}
