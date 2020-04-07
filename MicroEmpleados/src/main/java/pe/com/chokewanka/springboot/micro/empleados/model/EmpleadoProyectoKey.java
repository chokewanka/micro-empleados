package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmpleadoProyectoKey implements Serializable {

	@Column(name="proyecto_id")
	private Long proyectoId;
	
	@Column(name="empleado_id")
	private Long empleadoId;
	
	public EmpleadoProyectoKey() {}
	
	public Long getProyectoId() {
		return proyectoId;
	}

	public void setProyectoId(Long proyectoId) {
		this.proyectoId = proyectoId;
	}

	public Long getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}

	private static final long serialVersionUID = 5662013921765264890L;

}
