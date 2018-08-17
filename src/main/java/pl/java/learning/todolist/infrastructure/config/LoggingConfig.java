package pl.java.learning.todolist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.java.learning.todolist.infrastructure.logging.LoggingAspect;


@Configuration
class LoggingConfig {

  @Bean
  LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }
}
