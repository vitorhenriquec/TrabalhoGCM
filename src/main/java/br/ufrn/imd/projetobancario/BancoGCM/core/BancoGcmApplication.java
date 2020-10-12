package br.ufrn.imd.projetobancario.BancoGCM.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication(scanBasePackages = "br.ufrn.imd.projetobancario.BancoGCM")
@EnableJpaRepositories(basePackages = "br.ufrn.imd.projetobancario.BancoGCM")
@EntityScan("br.ufrn.imd.projetobancario.BancoGCM")
@EnableSpringDataWebSupport
@EnableJpaAuditing
public class BancoGcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoGcmApplication.class, args);
	}

}
