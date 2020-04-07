package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="conocimiento")
public class Conocimiento implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="conocimiento_id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne
    @JoinColumn(name="empleado_id", nullable=false)
	@JsonIgnore
    private Empleado empleado;
	
	@ManyToOne
    @JoinColumn(name="nivel_experiencia_id", nullable=false)
    private NivelExperiencia nivelExperiencia;
	
	public Conocimiento() {}
	
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public NivelExperiencia getNivelExperiencia() {
		return nivelExperiencia;
	}

	public void setNivelExperiencia(NivelExperiencia nivelExperiencia) {
		this.nivelExperiencia = nivelExperiencia;
	}
	
	@Override
	public String toString() {
		return "Conocimiento [id=" + id + ", nombre=" + nombre + "]";
	}

	private static final long serialVersionUID = 6212344105291525949L;

}
