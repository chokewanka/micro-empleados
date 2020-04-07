package pe.com.chokewanka.springboot.micro.empleados.service.impl;

import java.util.List;

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
		return (List<Cargo>) cargoRepository.findAll();
	}

	@Override
	public Cargo findById(Long id) {
		return cargoRepository.findById(id).orElse(null);
	}

}
