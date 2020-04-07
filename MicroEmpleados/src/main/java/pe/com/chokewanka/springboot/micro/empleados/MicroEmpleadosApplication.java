package pe.com.chokewanka.springboot.micro.empleados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableEurekaClient
//@RibbonClient(name="servicio-locales")
@EnableFeignClients
@SpringBootApplication
public class MicroEmpleadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroEmpleadosApplication.class, args);
	}

}
