package pe.com.chokewanka.springboot.micro.empleados.repository.custom;

import java.util.List;

import pe.com.chokewanka.springboot.micro.empleados.filter.EmpleadoFilter;
import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;

public interface EmpleadoRepositoryCustom {

	public List<Empleado> findCustom( EmpleadoFilter filter );
	
}
