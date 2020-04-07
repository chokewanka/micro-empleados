package pe.com.chokewanka.springboot.micro.empleados.service;

import java.util.List;

import pe.com.chokewanka.springboot.micro.empleados.model.Cargo;

public interface CargoService {

	public List<Cargo> findAll();
	
	public Cargo findById(Long id);
	
}
