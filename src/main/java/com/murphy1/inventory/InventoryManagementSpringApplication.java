package com.murphy1.inventory;

import com.murphy1.inventory.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class InventoryManagementSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSpringApplication.class, args);
    }

}
