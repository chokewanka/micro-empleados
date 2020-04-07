package pe.com.chokewanka.springboot.micro.empleados.repository.custom;

import java.util.List;

import pe.com.chokewanka.springboot.micro.empleados.filter.ProyectoFilter;
import pe.com.chokewanka.springboot.micro.empleados.model.Proyecto;

public interface ProyectoRepositoryCustom {

	public List<Proyecto> findCustom( ProyectoFilter filters );
	
}
