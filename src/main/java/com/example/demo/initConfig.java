package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class initConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository,TransporRepository transporRepository){
    return args -> {

        employee juan = new employee("Juan", "juanperez@gmail.com");
        employee maria = new employee("maria", "mariajuana@gmail.com");
        transport car1 = new transport(juan,"Carro nuevo1","3FQ14FS");
        transport car2 = new transport(maria,"Carro nuevo2","3TV24FS");


        employeeRepository.saveAll(List.of(maria,juan));
        transporRepository.saveAll(List.of(car1,car2));


    };
    }
}
