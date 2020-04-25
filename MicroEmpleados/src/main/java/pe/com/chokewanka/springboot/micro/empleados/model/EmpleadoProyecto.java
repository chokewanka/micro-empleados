package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "proyecto_x_empleado")
@AssociationOverrides({
	@AssociationOverride(name = "key.proyecto", 
		joinColumns = @JoinColumn(name = "proyecto_id")),
	@AssociationOverride(name = "key.empleado", 
		joinColumns = @JoinColumn(name = "empleado_id")) })
public class EmpleadoProyecto implements Serializable {

	@EmbeddedId
	private EmpleadoProyectoKey key = new EmpleadoProyectoKey();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;

	@Column(name = "responsabilidad")
	private String responsabilidad;
	
	public EmpleadoProyecto() {}
	
	public EmpleadoProyecto(Proyecto proyecto, Empleado empleado) {
		this.key = new EmpleadoProyectoKey(proyecto, empleado);
	}
	
	@JsonIgnore
	public EmpleadoProyectoKey getKey() {
		return key;
	}

	public void setPk(EmpleadoProyectoKey key) {
		this.key = key;
	}
	
	@Transient
	@JsonIgnore
	public Proyecto getProyecto() {
		return this.getKey().getProyecto();
	}

	public void setProyecto(Proyecto proyecto) {
		this.getKey().setProyecto(proyecto);
	}

	@Transient
	public Empleado getEmpleado() {
		return this.getKey().getEmpleado();
	}

	public void setEmpleado(Empleado empleado) {
		this.getKey().setEmpleado(empleado);
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

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		EmpleadoProyecto that = (EmpleadoProyecto) o;

		if (this.getKey() != null ? !this.getKey().equals(that.getKey()) : that.getKey() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (this.getKey() != null ? this.getKey().hashCode() : 0);
	}

	private static final long serialVersionUID = 559111892056880870L;

}
