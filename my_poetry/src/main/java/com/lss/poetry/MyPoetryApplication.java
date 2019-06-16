package com.lss.poetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.lss.poetry.mapper")
@ComponentScan(basePackages = { "com.lss.poetry","com.baidu.ai.aip", "org.n3r.idworker"})
public class MyPoetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPoetryApplication.class, args);
	}
}
