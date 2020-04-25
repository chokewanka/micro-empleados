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

import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;
import pe.com.chokewanka.springboot.micro.empleados.service.EmpleadoService;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	public EmpleadoService empleadoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Empleado> listar(@RequestParam Long idLocal){
		return empleadoService.findAll(idLocal);
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public List<Empleado> filtrar( @RequestParam Map<String,String> filters ){
		return empleadoService.filter(filters);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Empleado ver(@PathVariable Long id){
		return empleadoService.findById(id);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Long crear(@RequestBody Empleado empleado) {
		return empleadoService.create(empleado);
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void editar(@PathVariable Long id, @RequestBody Empleado empleado) {
		empleadoService.edit(id, empleado);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		empleadoService.delete(id);
	}
	
}
