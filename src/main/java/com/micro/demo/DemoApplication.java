package com.micro.demo;

import com.micro.demo.Controller.UserController;
import com.micro.demo.dao.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {
    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
//        UserController userController = new UserController(new UserDaoImpl());
//        System.out.println(userController.checkLicense(653254895235L));
    }

//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        return args -> {
//            Boolean response = restTemplate.getForObject(
//                    "http://localhost:8081/licenses/653254895235", Boolean.class);
//            log.info(response.toString());
//        };
//    }

}
