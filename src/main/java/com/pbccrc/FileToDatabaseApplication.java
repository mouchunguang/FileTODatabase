package com.pbccrc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@MapperScan("com.pbccrc.mapper")
public class FileToDatabaseApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(FileToDatabaseApplication.class, args);
	}
	
	@Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {  
        // TODO Auto-generated method stub  
        builder.sources(this.getClass());  
        return super.configure(builder);  
    }  
}


