package pe.com.chokewanka.springboot.micro.empleados.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.chokewanka.springboot.micro.empleados.model.Cargo;
import pe.com.chokewanka.springboot.micro.empleados.repository.CargoRepository;
import pe.com.chokewanka.springboot.micro.empleados.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	@Override
	public List<Cargo> findAll() {
		List<Cargo> dbCargos = (List<Cargo>) cargoRepository.findAll();
		
		List<Cargo> cargos = new ArrayList<Cargo>();
		for(Cargo dbCargo : dbCargos) {
			Cargo cargo = new Cargo();
			
			cargo.setId(dbCargo.getId());
			cargo.setNombre(dbCargo.getNombre());
			
			cargos.add(cargo);
		}
		
		return cargos;
	}

	@Override
	public Cargo findById(Long id) {
		Optional<Cargo> optionalCargo = cargoRepository.findById(id);
		
		if(optionalCargo.isPresent()) {
			Cargo dbCargo = optionalCargo.get();
			Cargo cargo = new Cargo();
			
			cargo.setId(dbCargo.getId());
			cargo.setNombre(dbCargo.getNombre());
		
			return cargo;
		}
		else {
			return new Cargo();
		}
	}

}
