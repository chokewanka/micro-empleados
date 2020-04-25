package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "empleado")
@Where(clause = "is_deleted = 0")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empleado_id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;

	@Transient
	private Local local;

	@Column(name = "local_id")
	private Long idLocal;
	
	@OneToMany(mappedBy="empleado", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Conocimiento> conocimientos = new ArrayList<Conocimiento>();

	@Column(name = "salario")
	private Double salario;

	@Column(name = "edad")
	private Integer edad;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "email")
	private String email;
	
	@OneToMany(mappedBy = "key.empleado", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<EmpleadoProyecto> empleadosProyecto = new ArrayList<EmpleadoProyecto>();

	@Column(name = "is_deleted")
	private Integer isDeleted;
	
	public Empleado() {}
	
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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

	public List<Conocimiento> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(List<Conocimiento> conocimientos) {
		this.conocimientos.clear();
	    if (conocimientos != null) {
	        this.conocimientos.addAll(conocimientos);
	    }
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<EmpleadoProyecto> getEmpleadosProyecto() {
		return this.empleadosProyecto;
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
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Empleado that = (Empleado) o;

		if (this.getId() != null ? !this.getId().equals(that.getId()) : that.getId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (this.getId() != null ? this.getId().hashCode() : 0);
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", idLocal=" + idLocal
				+ ", isDeleted=" + isDeleted + "]";
	}

	private static final long serialVersionUID = 3128200211540069507L;

}
