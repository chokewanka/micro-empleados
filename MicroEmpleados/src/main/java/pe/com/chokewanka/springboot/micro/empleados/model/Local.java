package pe.com.chokewanka.springboot.micro.empleados.model;

public class Local {

	private Long id;
	private String nombre;
	private String direccion;
	
	public Local() {}

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
}
