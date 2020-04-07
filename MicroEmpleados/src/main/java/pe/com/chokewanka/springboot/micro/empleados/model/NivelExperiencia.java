package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nivel_experiencia")
public class NivelExperiencia implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nivel_experiencia_id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	public NivelExperiencia() {}
	
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
	
	@Override
	public String toString() {
		return "NivelExperiencia [id=" + id + ", nombre=" + nombre + "]";
	}

	private static final long serialVersionUID = -5289076015223658400L;

}
