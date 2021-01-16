package ${groupId}.app;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = { "${groupId}"})
@EnableTransactionManagement
@MapperScan("${groupId}.repository.mapper")
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main( String[] args ) {

        SpringApplication.run(Application.class, args);

        logger.debug("--Application Started--");
    }
}
