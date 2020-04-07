package pe.com.chokewanka.springboot.micro.empleados.repository;

import org.springframework.data.repository.CrudRepository;

import pe.com.chokewanka.springboot.micro.empleados.model.Cargo;

public interface CargoRepository extends CrudRepository<Cargo, Long> {

}
