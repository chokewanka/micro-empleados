package pe.com.chokewanka.springboot.micro.empleados.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;
import pe.com.chokewanka.springboot.micro.empleados.repository.custom.EmpleadoRepositoryCustom;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>, EmpleadoRepositoryCustom {
	
	public List<Empleado> findByIdLocal( Long idLocal );
	
}
