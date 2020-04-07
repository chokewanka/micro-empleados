package pe.com.chokewanka.springboot.micro.empleados.clients.fallback;

import org.springframework.stereotype.Component;

import pe.com.chokewanka.springboot.micro.empleados.clients.LocalClient;
import pe.com.chokewanka.springboot.micro.empleados.model.Local;

@Component
public class LocalClientFallback implements LocalClient {

	@Override
	public Local ver(Long id) {
		return new Local();
	}

}
