package com.web.sell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@MapperScan(value = "com.web.sell.mapper")
public class SellApplication {

  public static void main(String[] args) {
    SpringApplication.run(SellApplication.class, args);
  }
}
