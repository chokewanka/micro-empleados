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
import pe.com.chokewanka.springboot.micro.empleados.model.Conocimiento;
import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;
import pe.com.chokewanka.springboot.micro.empleados.model.Local;
import pe.com.chokewanka.springboot.micro.empleados.model.NivelExperiencia;
import pe.com.chokewanka.springboot.micro.empleados.repository.EmpleadoRepository;
import pe.com.chokewanka.springboot.micro.empleados.repository.NivelExperienciaRepository;
import pe.com.chokewanka.springboot.micro.empleados.service.EmpleadoService;
import pe.com.chokewanka.springboot.micro.empleados.utils.ModelConstants;
import pe.com.chokewanka.springboot.micro.empleados.utils.UtilConstants;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private LocalClient localClient;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private NivelExperienciaRepository nivelExperienciaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll(Long idLocal) {
		List<Empleado> dbEmpleados = empleadoRepository.findByIdLocal(idLocal);
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		for(Empleado dbEmpleado : dbEmpleados) {
			Empleado empleado = new Empleado();
			
			empleado.setId(dbEmpleado.getId());
			empleado.setNombre(dbEmpleado.getNombre());
			empleado.setCodigo(dbEmpleado.getCodigo());
			empleado.setIdLocal(dbEmpleado.getIdLocal());
			empleado.setFechaIngreso(dbEmpleado.getFechaIngreso());
			
			empleados.add(empleado);
		}
		
		return empleados;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> filter(Map<String, String> filters) {
		EmpleadoFilter empleadoFilter = new EmpleadoFilter();
		
		int totalFilters = 0;
		for(Map.Entry<String, String> filter : filters.entrySet()) {
			
			String key = filter.getKey();
			String value = filter.getValue();
			
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
			default:
				return new ArrayList<Empleado>();
			}
			
		}
		
		if (totalFilters > 0) {
			List<Empleado> dbEmpleados = empleadoRepository.findCustom(empleadoFilter);
			
			List<Empleado> empleados = new ArrayList<Empleado>();
			for(Empleado dbEmpleado : dbEmpleados) {
				Empleado empleado = new Empleado();
				
				empleado.setId(dbEmpleado.getId());
				empleado.setNombre(dbEmpleado.getNombre());
				empleado.setCodigo(dbEmpleado.getCodigo());
				empleado.setIdLocal(dbEmpleado.getIdLocal());
				empleado.setFechaIngreso(dbEmpleado.getFechaIngreso());
				
				empleados.add(empleado);
			}
			
			return empleados;
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
			Empleado dbEmpleado = optionalEmpleado.get();
			Empleado empleado = new Empleado();
			
			empleado.setId(dbEmpleado.getId());
			empleado.setNombre(dbEmpleado.getNombre());
			empleado.setCodigo(dbEmpleado.getCodigo());
			empleado.setFechaIngreso(dbEmpleado.getFechaIngreso());
			
			Local dbLocal = localClient.ver(dbEmpleado.getIdLocal());
			
			Local local = new Local();
			local.setId(dbLocal.getId());
			local.setNombre(dbLocal.getNombre());
			empleado.setLocal(local);
			
			empleado.setSalario(dbEmpleado.getSalario());
			empleado.setEdad(dbEmpleado.getEdad());
			empleado.setTelefono(dbEmpleado.getTelefono());
			empleado.setEmail(dbEmpleado.getEmail());
			
			for(Conocimiento dbConocimiento : dbEmpleado.getConocimientos()) {
				Conocimiento conocimiento = new Conocimiento();
				conocimiento.setId(dbConocimiento.getId());
				conocimiento.setNombre(dbConocimiento.getNombre());
				
				NivelExperiencia dbNivelExperiencia = dbConocimiento.getNivelExperiencia();
				
				NivelExperiencia nivelExperiencia = new NivelExperiencia();
				nivelExperiencia.setId(dbNivelExperiencia.getId());
				nivelExperiencia.setNombre(dbNivelExperiencia.getNombre());
				conocimiento.setNivelExperiencia(nivelExperiencia);
				
				empleado.getConocimientos().add(conocimiento);
			}
			
			return empleado;
		}
		else {
			return new Empleado();
		}
	}

	@Override
	@Transactional
	public Long create(Empleado empleado) {
		if(empleado.getId() == null || empleado.getId().equals(UtilConstants.EMPTY_ID)) {
			Empleado newEmpleado = new Empleado();
			
			newEmpleado.setNombre(empleado.getNombre());
			newEmpleado.setCodigo(empleado.getCodigo());
			newEmpleado.setFechaIngreso(empleado.getFechaIngreso());
			newEmpleado.setIdLocal(empleado.getIdLocal());
			newEmpleado.setConocimientos(empleado.getConocimientos());
			newEmpleado.setSalario(empleado.getSalario());
			newEmpleado.setEdad(empleado.getEdad());
			newEmpleado.setTelefono(empleado.getTelefono());
			newEmpleado.setEmail(empleado.getEmail());
			newEmpleado.setIsDeleted(UtilConstants.IS_NOT_DELETED);
			
			List<Conocimiento> arrConocimiento = new ArrayList<Conocimiento>();
			for(Conocimiento conocimiento : empleado.getConocimientos()) {
				Conocimiento newConocimiento = new Conocimiento(newEmpleado);
				newConocimiento.setNombre(conocimiento.getNombre());
					
				Optional<NivelExperiencia> optNivelExperiencia = 
						nivelExperienciaRepository.findById(conocimiento.getNivelExperiencia().getId());
				if(optNivelExperiencia.isPresent()) {
					newConocimiento.setNivelExperiencia(optNivelExperiencia.get());
				}
				
				arrConocimiento.add(newConocimiento);
			}
			newEmpleado.setConocimientos(arrConocimiento);
			
			newEmpleado = empleadoRepository.save(newEmpleado);
			return newEmpleado.getId();
		}
		else {
			return 0L;
		}
	}

	@Override
	@Transactional
	public void edit(Long id, Empleado empleado) {
		if(id != null && !id.equals(UtilConstants.EMPTY_ID)) {
			Optional<Empleado> optEmpleado = empleadoRepository.findById(id);
			
			if(optEmpleado.isPresent()) {
				Empleado curEmpleado = optEmpleado.get();
				
				curEmpleado.setNombre(empleado.getNombre());
				curEmpleado.setCodigo(empleado.getCodigo());
				curEmpleado.setFechaIngreso(empleado.getFechaIngreso());
				curEmpleado.setIdLocal(empleado.getIdLocal());
				curEmpleado.setSalario(empleado.getSalario());
				curEmpleado.setEdad(empleado.getEdad());
				curEmpleado.setTelefono(empleado.getTelefono());
				curEmpleado.setEmail(empleado.getEmail());
				
				List<Conocimiento> arrConocimiento = new ArrayList<Conocimiento>();
				for(Conocimiento conocimiento : empleado.getConocimientos()) {
					Conocimiento curConocimiento;
					
					if(conocimiento.getId() != null && !conocimiento.getId().equals(UtilConstants.EMPTY_ID)) {
						int i = curEmpleado.getConocimientos().indexOf(conocimiento);
						
						if(i >= 0) {
							curConocimiento = curEmpleado.getConocimientos().get(i);
						}
						else {
							curConocimiento = new Conocimiento(curEmpleado);
						}
					}
					else {
						curConocimiento = new Conocimiento(curEmpleado);
					}
					
					curConocimiento.setNombre(conocimiento.getNombre());
					
					Optional<NivelExperiencia> optNivelExperiencia = 
							nivelExperienciaRepository.findById(conocimiento.getNivelExperiencia().getId());
					if(optNivelExperiencia.isPresent()) {
						curConocimiento.setNivelExperiencia(optNivelExperiencia.get());
					}
					
					arrConocimiento.add(curConocimiento);
				}
				curEmpleado.setConocimientos(arrConocimiento);
				
				empleadoRepository.save(curEmpleado);
			}
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
