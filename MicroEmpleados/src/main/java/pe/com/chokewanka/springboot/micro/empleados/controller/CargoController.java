package pe.com.chokewanka.springboot.micro.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.chokewanka.springboot.micro.empleados.model.Cargo;
import pe.com.chokewanka.springboot.micro.empleados.service.CargoService;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	public CargoService cargoService;
	
	@GetMapping("/")
	public List<Cargo> listar(){
		return cargoService.findAll();
	}
	
}
