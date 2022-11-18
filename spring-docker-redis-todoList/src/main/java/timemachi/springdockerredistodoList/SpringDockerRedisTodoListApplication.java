package timemachi.springdockerredistodoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringDockerRedisTodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDockerRedisTodoListApplication.class, args);
	}

}
