package com.nc.calculator.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nc.calculator.v1.model.CalculationDTO;
import com.nc.calculator.v1.service.ExpressionBuilderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/")
public class CalculatorController {
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
	
	@Autowired
	ExpressionBuilderService expressionBuilderService;
	
	@PostMapping("calculator")
	public String calculate(@RequestBody CalculationDTO calculationDTO )
	{
		log.info("Enter calculate method of CalculatorController");
		String result = expressionBuilderService.evaluate(calculationDTO.getFormula());
		log.info("Exit calculate method of CalculatorController");
		return result;
	}

}
