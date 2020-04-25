package pe.com.chokewanka.springboot.micro.empleados.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pe.com.chokewanka.springboot.micro.empleados.filter.EmpleadoFilter;
import pe.com.chokewanka.springboot.micro.empleados.model.Empleado;
import pe.com.chokewanka.springboot.micro.empleados.repository.custom.EmpleadoRepositoryCustom;
import pe.com.chokewanka.springboot.micro.empleados.utils.ModelConstants;
import pe.com.chokewanka.springboot.micro.empleados.utils.UtilConstants;

public class EmpleadoRepositoryCustomImpl implements EmpleadoRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Empleado> findCustom(EmpleadoFilter filter) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		List<Empleado> resultado = new ArrayList<Empleado>();
		
		CriteriaQuery<Empleado> query = cb.createQuery(Empleado.class);
		Root<Empleado> empleado = query.from(Empleado.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(filter.getNombre() != null && !filter.getNombre().isEmpty()) {
			predicates.add(cb.like(empleado.get(ModelConstants.EMPLEADO_NOMBRE), UtilConstants.bothSidesLike(filter.getNombre())));
		}
		
		if(filter.getCodigo() != null && !filter.getCodigo().isEmpty()) {
			predicates.add(cb.like(empleado.get(ModelConstants.EMPLEADO_CODIGO), UtilConstants.rightSideLike(filter.getCodigo())));
		}
		
		if(filter.getFechaInicio() != null) {
			predicates.add(cb.greaterThanOrEqualTo(empleado.get(ModelConstants.EMPLEADO_FECHA_INGRESO), filter.getFechaInicio()));
		}
		
		if(filter.getFechaFin() != null) {
			predicates.add(cb.lessThanOrEqualTo(empleado.get(ModelConstants.EMPLEADO_FECHA_INGRESO), filter.getFechaFin()));
		}
		
		if(filter.getIdLocal() != null && filter.getIdLocal()>UtilConstants.EMPTY_ID) {
			predicates.add(cb.equal(empleado.get(ModelConstants.EMPLEADO_LOCAL), filter.getIdLocal()));
		}
		
		query
			.select(empleado)
			.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		resultado = entityManager.createQuery(query).getResultList();
		
		return resultado;
		
	}

}
