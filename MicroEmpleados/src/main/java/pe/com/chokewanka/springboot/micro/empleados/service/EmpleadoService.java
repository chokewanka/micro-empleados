package pe.com.chokewanka.springboot.micro.empleados.service;

import java.util.List;
import java.util.Map;

import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;

public interface EmpleadoService {

	public List<Empleado> findAll(Long idLocal);
	
	public List<Empleado> filter(Map<String,String> filters);
	
	public Empleado findById(Long id);
	
	public Long create(Empleado empleado);
	
	public void edit(Long id, Empleado empleado);
	
	public void delete(Long id);
	
}
