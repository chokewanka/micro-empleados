package pe.com.chokewanka.springboot.micro.empleados.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pe.com.chokewanka.springboot.micro.empleados.model.Proyecto;
import pe.com.chokewanka.springboot.micro.empleados.repository.custom.ProyectoRepositoryCustom;

public interface ProyectoRepository extends CrudRepository<Proyecto, Long>, ProyectoRepositoryCustom {

	public List<Proyecto> findByIdLocal( Long idLocal );
	
}
