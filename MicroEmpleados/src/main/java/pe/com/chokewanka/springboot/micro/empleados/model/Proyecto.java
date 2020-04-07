package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

@Entity
@Table(name="proyecto")
@Where(clause = "is_deleted = 0")
public class Proyecto implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="proyecto_id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Transient
	private Local local;

	@Column(name = "local_id")
	private Long idLocal;
	
	@Column(name="fecha_inicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@Column(name="fecha_fin")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	@OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmpleadoProyecto> empleadosProyecto = new ArrayList<EmpleadoProyecto>();
	
	@Column(name="is_deleted")
	private Integer isDeleted;
	
	public Proyecto() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<EmpleadoProyecto> getEmpleadosProyecto() {
		return empleadosProyecto;
	}

	public void setEmpleadosProyecto(List<EmpleadoProyecto> empleadosProyecto) {
		this.empleadosProyecto = empleadosProyecto;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", idLocal=" + idLocal
				+ ", isDeleted=" + isDeleted + "]";
	}

	private static final long serialVersionUID = 6099003564651270139L;

}
