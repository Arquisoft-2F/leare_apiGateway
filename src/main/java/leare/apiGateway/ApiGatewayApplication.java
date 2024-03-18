package leare.apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Para que lo sepan este es el main esta metido en el pom que este sea el main
//si cambian carpetas orden o nombres toca cambiar el pom
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		System.out.println("Test");
	
		//call the graphql initiliazier
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
