package pe.com.chokewanka.springboot.micro.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.chokewanka.springboot.micro.empleados.model.NivelExperiencia;
import pe.com.chokewanka.springboot.micro.empleados.service.NivelExperienciaService;

@RestController
@RequestMapping("/nivel-experiencia")
public class NivelExperienciaController {

	@Autowired
	public NivelExperienciaService nivelExperienciaService;
	
	@GetMapping("/")
	public List<NivelExperiencia> listar(){
		return nivelExperienciaService.findAll();
	}
	
}
