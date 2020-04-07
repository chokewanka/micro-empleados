package pe.com.chokewanka.springboot.micro.empleados.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.chokewanka.springboot.micro.empleados.model.NivelExperiencia;
import pe.com.chokewanka.springboot.micro.empleados.repository.NivelExperienciaRepository;
import pe.com.chokewanka.springboot.micro.empleados.service.NivelExperienciaService;

@Service
public class NivelExperienciaServiceImpl implements NivelExperienciaService {

	@Autowired
	private NivelExperienciaRepository nivelExperienciaRepository;
	
	@Override
	public List<NivelExperiencia> findAll() {
		return (List<NivelExperiencia>) nivelExperienciaRepository.findAll();
	}

	@Override
	public NivelExperiencia findById(Long id) {
		return nivelExperienciaRepository.findById(id).orElse(null);
	}

}
