package pe.com.chokewanka.springboot.micro.empleados.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.chokewanka.springboot.micro.empleados.clients.LocalClient;
import pe.com.chokewanka.springboot.micro.empleados.filter.ProyectoFilter;
import pe.com.chokewanka.springboot.micro.empleados.model.Local;
import pe.com.chokewanka.springboot.micro.empleados.model.Proyecto;
import pe.com.chokewanka.springboot.micro.empleados.repository.ProyectoRepository;
import pe.com.chokewanka.springboot.micro.empleados.service.ProyectoService;
import pe.com.chokewanka.springboot.micro.empleados.utils.ModelConstants;
import pe.com.chokewanka.springboot.micro.empleados.utils.UtilConstants;

@Service
public class ProyectoServiceImpl implements ProyectoService {

	@Autowired
	private LocalClient localClient;
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> findAll(Long idLocal) {
		return (List<Proyecto>) proyectoRepository.findByIdLocal(idLocal);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> filter(Map<String, String> filters) {
		
		ProyectoFilter proyectoFilter = new ProyectoFilter();
		
		String key;
		String value;
		int totalFilters = 0;
		
		for(Map.Entry<String, String> filter : filters.entrySet()) {
			
			key = filter.getKey();
			value = filter.getValue();
			
			switch (key) {
			case ModelConstants.PROYECTO_NOMBRE:
				if(value != null && !value.trim().equals(UtilConstants.EMPTY_STRING)) {
					proyectoFilter.setNombre(value.trim());
					totalFilters++;
				}
				
				break;
			case ModelConstants.PROYECTO_FECHA_INICIO:
				if(value != null) {
					try {
        				Date dateFormatted = new SimpleDateFormat(UtilConstants.DATE_FORMAT).parse(value);
        				proyectoFilter.setFechaInicio(dateFormatted);
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        			}

					totalFilters++;
				}
				
				break;
			case ModelConstants.PROYECTO_FECHA_FIN:
				if(value != null) {
					try {
        				Date dateFormatted = new SimpleDateFormat(UtilConstants.DATE_FORMAT).parse(value);
        				proyectoFilter.setFechaFin(dateFormatted);
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        			}

					totalFilters++;
				}
				
				break;
			case ModelConstants.PROYECTO_LOCAL:
				if(value != null && !value.isEmpty() && Long.parseLong(value)>0 ) {
					proyectoFilter.setIdLocal(Long.parseLong(value));
					totalFilters++;
				}
				
				break;
			}
			
		}
		
		if (totalFilters > 0) {
			return (List<Proyecto>) proyectoRepository.findCustom(proyectoFilter);
		}
		else {
			return new ArrayList<Proyecto>();
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Proyecto findById(Long id) {
		
		Optional<Proyecto> optionalProyecto = proyectoRepository.findById(id);
		
		if(optionalProyecto.isPresent()) {
			Proyecto proyecto = optionalProyecto.get();
			
			Local local = localClient.ver(proyecto.getIdLocal());
			proyecto.setLocal(local);
			
			return proyecto;
		}
		else {
			return new Proyecto();
		}
		
	}

	@Override
	@Transactional
	public Proyecto save(Proyecto proyecto) {

		Long id = proyecto.getId();
		if(id == null || id.equals(UtilConstants.EMPTY_ID)) {
			proyecto.setIsDeleted(UtilConstants.IS_NOT_DELETED);
		}
		
		Optional<Proyecto> optionalProyecto = proyectoRepository.findById(id);
		
		if(optionalProyecto.isPresent()) {
			proyecto.setIsDeleted(UtilConstants.IS_DELETED);
			return proyectoRepository.save(proyecto);
		}
		else {
			return new Proyecto();
		}
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		Optional<Proyecto> optionalProyecto = proyectoRepository.findById(id);
		
		if(optionalProyecto.isPresent()) {
			Proyecto proyecto = optionalProyecto.get();
			
			proyecto.setIsDeleted(UtilConstants.IS_DELETED);
			proyectoRepository.save(proyecto);
		}
		
	}

}
