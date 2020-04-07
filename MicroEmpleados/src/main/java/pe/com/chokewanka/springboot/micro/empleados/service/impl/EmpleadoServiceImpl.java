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
import pe.com.chokewanka.springboot.micro.empleados.filter.EmpleadoFilter;
import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;
import pe.com.chokewanka.springboot.micro.empleados.model.Local;
import pe.com.chokewanka.springboot.micro.empleados.repository.EmpleadoRepository;
import pe.com.chokewanka.springboot.micro.empleados.service.EmpleadoService;
import pe.com.chokewanka.springboot.micro.empleados.utils.ModelConstants;
import pe.com.chokewanka.springboot.micro.empleados.utils.UtilConstants;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private LocalClient localClient;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll(Long idLocal) {
		return (List<Empleado>) empleadoRepository.findByIdLocal(idLocal);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> filter(Map<String, String> filters) {

		EmpleadoFilter empleadoFilter = new EmpleadoFilter();
		
		String key;
		String value;
		int totalFilters = 0;
		
		for(Map.Entry<String, String> filter : filters.entrySet()) {
			
			key = filter.getKey();
			value = filter.getValue();
			
			switch (key) {
			case ModelConstants.EMPLEADO_NOMBRE:
				if(value != null && !value.trim().equals(UtilConstants.EMPTY_STRING)) {
					empleadoFilter.setNombre(value.trim());
					totalFilters++;
				}
				
				break;
			case ModelConstants.EMPLEADO_CODIGO:
				if(value != null && !value.trim().equals(UtilConstants.EMPTY_STRING)) {
					empleadoFilter.setCodigo(value.trim());
					totalFilters++;
				}
				
				break;
			case ModelConstants.EMPLEADO_FECHA_INICIO:
				if(value != null) {
					try {
        				Date dateFormatted = new SimpleDateFormat(UtilConstants.DATE_FORMAT).parse(value);
        				empleadoFilter.setFechaInicio(dateFormatted);
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        			}

					totalFilters++;
				}
				
				break;
			case ModelConstants.EMPLEADO_FECHA_FIN:
				if(value != null) {
					try {
        				Date dateFormatted = new SimpleDateFormat(UtilConstants.DATE_FORMAT).parse(value);
        				empleadoFilter.setFechaFin(dateFormatted);
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        			}

					totalFilters++;
				}
				
				break;
			case ModelConstants.EMPLEADO_LOCAL:
				if(value != null && !value.isEmpty() && Long.parseLong(value)>0 ) {
					empleadoFilter.setIdLocal(Long.parseLong(value));
					totalFilters++;
				}
				
				break;
			}
			
		}
		
		if (totalFilters > 0) {
			return (List<Empleado>) empleadoRepository.findCustom(empleadoFilter);
		}
		else {
			return new ArrayList<Empleado>();
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findById(Long id) {
		
		Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
		
		if(optionalEmpleado.isPresent()) {
			Empleado empleado = optionalEmpleado.get();
			
			Local local = localClient.ver(empleado.getIdLocal());
			empleado.setLocal(local);
			
			return empleado;
		}
		else {
			return new Empleado();
		}
		
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		
		Long id = empleado.getId();
		if(id == null || id.equals(UtilConstants.EMPTY_ID)) {
			empleado.setIsDeleted(UtilConstants.IS_NOT_DELETED);
		}
		
		Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
		
		if(optionalEmpleado.isPresent()) {
			empleado.setIsDeleted(UtilConstants.IS_DELETED);
			return empleadoRepository.save(empleado);
		}
		else {
			return new Empleado();
		}
		
	}

	@Override
	@Transactional
	public void delete(Long id) {

		Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
		
		if(optionalEmpleado.isPresent()) {
			Empleado empleado = optionalEmpleado.get();
			
			empleado.setIsDeleted(UtilConstants.IS_DELETED);
			empleadoRepository.save(empleado);
		}
		
	}

}
