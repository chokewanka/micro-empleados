package pe.com.chokewanka.springboot.micro.empleados.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.com.chokewanka.springboot.micro.empleados.clients.fallback.LocalClientFallback;
import pe.com.chokewanka.springboot.micro.empleados.model.Local;

@FeignClient(name="servicio-locales", fallback = LocalClientFallback.class)
public interface LocalClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/local/{id}")
	public Local ver(@PathVariable Long id);
	
}
