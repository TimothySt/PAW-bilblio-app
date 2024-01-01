package biblio.web.biblio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
public class BiblioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioApplication.class, args);
	}

}
