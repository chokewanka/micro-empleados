package pe.com.chokewanka.springboot.micro.empleados.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cargo")
public class Cargo implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cargo_id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	public Cargo() {}
	
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
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Cargo that = (Cargo) o;

		if (this.getId() != null ? !this.getId().equals(that.getId()) : that.getId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (this.getId() != null ? this.getId().hashCode() : 0);
	}
	
	@Override
	public String toString() {
		return "Cargo [id=" + id + ", nombre=" + nombre + "]";
	}

	private static final long serialVersionUID = 1575919178764764197L;

}
