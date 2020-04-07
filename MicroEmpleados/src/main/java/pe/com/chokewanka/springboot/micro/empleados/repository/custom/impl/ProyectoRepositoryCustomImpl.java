package pe.com.chokewanka.springboot.micro.empleados.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pe.com.chokewanka.springboot.micro.empleados.filter.ProyectoFilter;
import pe.com.chokewanka.springboot.micro.empleados.model.Proyecto;
import pe.com.chokewanka.springboot.micro.empleados.repository.custom.ProyectoRepositoryCustom;
import pe.com.chokewanka.springboot.micro.empleados.utils.ModelConstants;
import pe.com.chokewanka.springboot.micro.empleados.utils.UtilConstants;

public class ProyectoRepositoryCustomImpl implements ProyectoRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Proyecto> findCustom(ProyectoFilter filter) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		List<Proyecto> resultado = new ArrayList<Proyecto>();
		
		CriteriaQuery<Proyecto> query = cb.createQuery(Proyecto.class);
		Root<Proyecto> proyecto = query.from(Proyecto.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(filter.getNombre() != null && !filter.getNombre().isEmpty()) {
			predicates.add(cb.like(proyecto.get(ModelConstants.PROYECTO_NOMBRE), UtilConstants.bothSidesLike(filter.getNombre())));
		}
		
		if(filter.getFechaInicio() != null) {
			predicates.add(cb.greaterThanOrEqualTo(proyecto.get(ModelConstants.PROYECTO_FECHA_INICIO), filter.getFechaInicio()));
		}
		
		if(filter.getFechaFin() != null) {
			predicates.add(cb.lessThanOrEqualTo(proyecto.get(ModelConstants.PROYECTO_FECHA_FIN), filter.getFechaFin()));
		}
		
		if(filter.getIdLocal() != null && filter.getIdLocal()>UtilConstants.EMPTY_ID) {
			predicates.add(cb.equal(proyecto.get(ModelConstants.PROYECTO_LOCAL), filter.getIdLocal()));
		}
		
		query
			.select(proyecto)
			.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		resultado = entityManager.createQuery(query).getResultList();
		
		return resultado;
		
	}
	
}
