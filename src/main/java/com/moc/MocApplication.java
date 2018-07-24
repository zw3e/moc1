package com.moc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.moc.*.mapper")
@EnableCaching
public class MocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MocApplication.class, args);
	}
}
