package br.com.rotaractsorocabaleste.rotasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RotasalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RotasalesApplication.class, args);
	}

}
