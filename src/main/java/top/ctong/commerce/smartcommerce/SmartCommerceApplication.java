package top.ctong.commerce.smartcommerce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Clover
 */
@SpringBootApplication
@MapperScan("top.ctong.commerce.smartcommerce.dao")
public class SmartCommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCommerceApplication.class, args);
    }

}
