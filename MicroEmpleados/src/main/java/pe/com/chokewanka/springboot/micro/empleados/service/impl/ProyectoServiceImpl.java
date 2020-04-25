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
import pe.com.chokewanka.springboot.micro.empleados.model.Cargo;
import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;
import pe.com.chokewanka.springboot.micro.empleados.model.EmpleadoProyecto;
import pe.com.chokewanka.springboot.micro.empleados.model.Local;
import pe.com.chokewanka.springboot.micro.empleados.model.Proyecto;
import pe.com.chokewanka.springboot.micro.empleados.repository.CargoRepository;
import pe.com.chokewanka.springboot.micro.empleados.repository.EmpleadoRepository;
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
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> findAll(Long idLocal) {
		List<Proyecto> dbProyectos = proyectoRepository.findByIdLocal(idLocal);
		
		List<Proyecto> proyectos = new ArrayList<Proyecto>();
		for(Proyecto dbProyecto : dbProyectos) {
			Proyecto proyecto = new Proyecto();
			
			proyecto.setId(dbProyecto.getId());
			proyecto.setNombre(dbProyecto.getNombre());
			proyecto.setDescripcion(dbProyecto.getDescripcion());
			proyecto.setIdLocal(dbProyecto.getIdLocal());
			proyecto.setFechaInicio(dbProyecto.getFechaInicio());
			proyecto.setFechaFin(dbProyecto.getFechaFin());
			
			proyectos.add(proyecto);
		}
		
		return proyectos;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> filter(Map<String, String> filters) {
		
		ProyectoFilter proyectoFilter = new ProyectoFilter();
		
		int totalFilters = 0;
		for(Map.Entry<String, String> filter : filters.entrySet()) {
			
			String key = filter.getKey();
			String value = filter.getValue();
			
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
			default:
				return new ArrayList<Proyecto>();
			}
			
		}
		
		if(totalFilters > 0) {
			List<Proyecto> dbProyectos = proyectoRepository.findCustom(proyectoFilter);
			
			List<Proyecto> proyectos = new ArrayList<Proyecto>();
			for(Proyecto dbProyecto : dbProyectos) {
				Proyecto proyecto = new Proyecto();
				
				proyecto.setId(dbProyecto.getId());
				proyecto.setNombre(dbProyecto.getNombre());
				proyecto.setDescripcion(dbProyecto.getDescripcion());
				proyecto.setIdLocal(dbProyecto.getIdLocal());
				proyecto.setFechaInicio(dbProyecto.getFechaInicio());
				proyecto.setFechaFin(dbProyecto.getFechaFin());
				
				proyectos.add(proyecto);
			}
			
			return proyectos;
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
			Proyecto dbProyecto = optionalProyecto.get();
			Proyecto proyecto = new Proyecto();
			
			proyecto.setId(dbProyecto.getId());
			proyecto.setNombre(dbProyecto.getNombre());
			proyecto.setDescripcion(dbProyecto.getDescripcion());
			
			Local dbLocal = localClient.ver(dbProyecto.getIdLocal());
			
			Local local = new Local();
			local.setId(dbLocal.getId());
			local.setNombre(dbLocal.getNombre());
			proyecto.setLocal(local);
			
			proyecto.setFechaInicio(dbProyecto.getFechaInicio());
			proyecto.setFechaFin(dbProyecto.getFechaFin());
			
			for(EmpleadoProyecto dbEmpleadoProyecto : dbProyecto.getEmpleadosProyecto()) {
				Empleado dbEmpleado = dbEmpleadoProyecto.getEmpleado();
				
				Empleado empleado = new Empleado();
				empleado.setId(dbEmpleado.getId());
				empleado.setNombre(dbEmpleado.getNombre());
				empleado.setCodigo(dbEmpleado.getCodigo());
				
				EmpleadoProyecto empleadoProyecto = new EmpleadoProyecto();
				empleadoProyecto.setEmpleado(empleado);
				
				Cargo dbCargo = dbEmpleadoProyecto.getCargo();
				
				Cargo cargo = new Cargo();
				cargo.setId(dbCargo.getId());
				cargo.setNombre(dbCargo.getNombre());
				empleadoProyecto.setCargo(cargo);
				
				empleadoProyecto.setResponsabilidad(dbEmpleadoProyecto.getResponsabilidad());
				
				proyecto.getEmpleadosProyecto().add(empleadoProyecto);
			}
			
			return proyecto;
		}
		else {
			return new Proyecto();
		}
		
	}

	@Override
	@Transactional
	public Long create(Proyecto proyecto) {
		if(proyecto.getId() == null || proyecto.getId().equals(UtilConstants.EMPTY_ID)) {
			Proyecto newProyecto = new Proyecto();
			
			newProyecto.setNombre(proyecto.getNombre());
			newProyecto.setDescripcion(proyecto.getDescripcion());
			newProyecto.setIdLocal(proyecto.getIdLocal());
			newProyecto.setFechaInicio(proyecto.getFechaInicio());
			newProyecto.setFechaFin(proyecto.getFechaFin());
			newProyecto.setIsDeleted(UtilConstants.IS_NOT_DELETED);

			List<EmpleadoProyecto> arrEmpleadoProyecto = new ArrayList<EmpleadoProyecto>();
			for(EmpleadoProyecto empleadoProyecto : proyecto.getEmpleadosProyecto()) {
				Optional<Empleado> optEmpleado = empleadoRepository.findById(empleadoProyecto.getEmpleado().getId());

				if(optEmpleado.isPresent()) {
					EmpleadoProyecto newEmpleadoProyecto = new EmpleadoProyecto(newProyecto, optEmpleado.get());
					
					Optional<Cargo> optCargo = cargoRepository.findById(empleadoProyecto.getCargo().getId());
					if(optCargo.isPresent()) {
						newEmpleadoProyecto.setCargo(optCargo.get());
					}
					
					newEmpleadoProyecto.setResponsabilidad(empleadoProyecto.getResponsabilidad());
					
					arrEmpleadoProyecto.add(newEmpleadoProyecto);
				}
			}
			newProyecto.setEmpleadosProyecto(arrEmpleadoProyecto);
			
			newProyecto = proyectoRepository.save(newProyecto);
			return newProyecto.getId();
		}
		else {
			return 0L;
		}
	}
	
	@Override
	@Transactional
	public void edit(Long id, Proyecto proyecto) {
		if(id != null && !id.equals(UtilConstants.EMPTY_ID)) {
			Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
			
			if(optProyecto.isPresent()) {
				Proyecto curProyecto = optProyecto.get();
				
				curProyecto.setNombre(proyecto.getNombre());
				curProyecto.setDescripcion(proyecto.getDescripcion());
				curProyecto.setIdLocal(proyecto.getIdLocal());
				curProyecto.setFechaInicio(proyecto.getFechaInicio());
				curProyecto.setFechaFin(proyecto.getFechaFin());
				
				List<EmpleadoProyecto> arrEmpleadoProyecto = new ArrayList<EmpleadoProyecto>();
				for(EmpleadoProyecto empleadoProyecto : proyecto.getEmpleadosProyecto()) {
					Optional<Empleado> optEmpleado = empleadoRepository.findById(empleadoProyecto.getEmpleado().getId());

					if(optEmpleado.isPresent()) {
						EmpleadoProyecto curEmpleadoProyecto;
						
						int i = curProyecto.getEmpleadosProyecto().indexOf(empleadoProyecto);
						if(i >= 0) {
							curEmpleadoProyecto = curProyecto.getEmpleadosProyecto().get(i);
						}
						else {
							curEmpleadoProyecto = new EmpleadoProyecto(curProyecto, optEmpleado.get());
						}
						
						Optional<Cargo> optCargo = cargoRepository.findById(empleadoProyecto.getCargo().getId());
						if(optCargo.isPresent()) {
							curEmpleadoProyecto.setCargo(optCargo.get());
						}
						
						curEmpleadoProyecto.setResponsabilidad(empleadoProyecto.getResponsabilidad());
						
						arrEmpleadoProyecto.add(curEmpleadoProyecto);
					}
				}
				curProyecto.setEmpleadosProyecto(arrEmpleadoProyecto);
				
				proyectoRepository.save(curProyecto);
			}
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
