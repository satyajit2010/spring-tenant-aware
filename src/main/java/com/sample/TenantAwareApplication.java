package com.sample;

import com.sample.tenantaware.MultiTenantBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TenantAwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantAwareApplication.class, args);
	}

	@Bean
	public MultiTenantBeanPostProcessor multiTenantAwarePostProcessor(){
		return new MultiTenantBeanPostProcessor();
	}

}
