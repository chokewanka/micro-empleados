package pe.com.chokewanka.springboot.micro.empleados.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.chokewanka.springboot.micro.empleados.model.Proyecto;
import pe.com.chokewanka.springboot.micro.empleados.service.ProyectoService;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

	@Autowired
	public ProyectoService proyectoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Proyecto> listar(Long idLocal){
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
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Proyecto guardar(@RequestBody Proyecto proyecto) {
		return proyectoService.save(proyecto);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam Long id) {
		proyectoService.delete(id);
	}
	
}
