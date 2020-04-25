package pe.com.chokewanka.springboot.micro.empleados.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		List<NivelExperiencia> dbNivelesExperiencia = (List<NivelExperiencia>) nivelExperienciaRepository.findAll();
		
		List<NivelExperiencia> nivelesExperiencia = new ArrayList<NivelExperiencia>();
		for(NivelExperiencia dbNivelExperiencia : dbNivelesExperiencia) {
			NivelExperiencia nivelExperiencia = new NivelExperiencia();
			
			nivelExperiencia.setId(dbNivelExperiencia.getId());
			nivelExperiencia.setNombre(dbNivelExperiencia.getNombre());
			
			nivelesExperiencia.add(nivelExperiencia);
		}
		
		return nivelesExperiencia;
	}

	@Override
	public NivelExperiencia findById(Long id) {
		Optional<NivelExperiencia> optionalNivelExperiencia = nivelExperienciaRepository.findById(id);
		
		if(optionalNivelExperiencia.isPresent()) {
			NivelExperiencia dbNivelExperiencia = optionalNivelExperiencia.get();
			NivelExperiencia nivelExperiencia = new NivelExperiencia();
			
			nivelExperiencia.setId(dbNivelExperiencia.getId());
			nivelExperiencia.setNombre(dbNivelExperiencia.getNombre());
		
			return nivelExperiencia;
		}
		else {
			return new NivelExperiencia();
		}
	}

}
