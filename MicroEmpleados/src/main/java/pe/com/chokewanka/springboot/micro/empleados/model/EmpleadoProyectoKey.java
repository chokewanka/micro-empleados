package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class EmpleadoProyectoKey implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("proyecto_id")
	@JoinColumn(name = "proyecto_id")
	@JsonIgnore
	private Proyecto proyecto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("empleado_id")
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;
	
	public EmpleadoProyectoKey() {}
	
	public EmpleadoProyectoKey(Proyecto proyecto, Empleado empleado) {
		super();
		this.proyecto = proyecto;
		this.empleado = empleado;
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
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadoProyectoKey that = (EmpleadoProyectoKey) o;

        if (this.proyecto != null ? !this.proyecto.equals(that.proyecto) : that.proyecto != null) return false;
        if (this.empleado != null ? !this.empleado.equals(that.empleado) : that.empleado != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (this.proyecto != null ? this.proyecto.hashCode() : 0);
        result = 31 * result + (this.empleado != null ? this.empleado.hashCode() : 0);
        return result;
    }

	private static final long serialVersionUID = 5662013921765264890L;

}
