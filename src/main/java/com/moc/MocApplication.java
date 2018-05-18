package com.moc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.moc.*.mapper")
public class MocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MocApplication.class, args);
	}
}
