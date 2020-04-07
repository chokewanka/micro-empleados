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

import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;
import pe.com.chokewanka.springboot.micro.empleados.service.EmpleadoService;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	public EmpleadoService empleadoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Empleado> listar(Long idLocal){
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
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Empleado guardar(@RequestBody Empleado empleado) {
		return empleadoService.save(empleado);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam Long id) {
		empleadoService.delete(id);
	}
	
}
