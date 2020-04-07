package pe.com.chokewanka.springboot.micro.empleados.service;

import java.util.List;

import pe.com.chokewanka.springboot.micro.empleados.model.NivelExperiencia;

public interface NivelExperienciaService {

	public List<NivelExperiencia> findAll();
	
	public NivelExperiencia findById(Long id);
	
}
