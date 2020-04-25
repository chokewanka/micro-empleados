package pe.com.chokewanka.springboot.micro.empleados.service;

import java.util.List;
import java.util.Map;

import pe.com.chokewanka.springboot.micro.empleados.model.Proyecto;

public interface ProyectoService {

	public List<Proyecto> findAll(Long idLocal);
	
	public List<Proyecto> filter(Map<String,String> filters);
	
	public Proyecto findById(Long id);
	
	public Long create(Proyecto proyecto);
	
	public void edit(Long id, Proyecto proyecto);
	
	public void delete(Long id);
	
}
