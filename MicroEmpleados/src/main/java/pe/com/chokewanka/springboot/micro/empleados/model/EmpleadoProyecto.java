package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "proyecto_x_empleado")
public class EmpleadoProyecto implements Serializable {

	@EmbeddedId
	private EmpleadoProyectoKey id;

	@ManyToOne
	@MapsId("proyecto_id")
	@JoinColumn(name = "proyecto_id")
	@JsonIgnore
	private Proyecto proyecto;

	@ManyToOne
	@MapsId("empleado_id")
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;

	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;

	@Column(name = "responsabilidad")
	private String responsabilidad;
	
	public EmpleadoProyecto() {}
	
	public EmpleadoProyectoKey getId() {
		return id;
	}

	public void setId(EmpleadoProyectoKey id) {
		this.id = id;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getResponsabilidad() {
		return responsabilidad;
	}

	public void setResponsabilidad(String responsabilidad) {
		this.responsabilidad = responsabilidad;
	}

	private static final long serialVersionUID = 559111892056880870L;

}
