package com.boot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
  @Bean
  CommandLineRunner commandLineRunner(StudentRepository repository) {
    return args -> {
      Student faouzi = new Student("Faouzi Mohamed", "me@me.com",
                                   LocalDate.of(1997, FEBRUARY, 12));
      Student karim = new Student("Karim Maolida", "yaser@me.com",
                                  LocalDate.of(1987, MARCH, 1));
      Student douze = new Student("Docteur douze", "douze@me.com",
                                  LocalDate.of(2004, NOVEMBER, 25));

      var halim_mohamed = new Student("Halim Mohamed", "halim@me.com",
                                      LocalDate.of(2004, JANUARY, 12));
      repository.saveAll(List.of(faouzi, karim, douze, halim_mohamed));
    };
  }
}
