package com.nc.calculator.v1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.calculator.expressionevaluator.ExpressionBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExpressionBuilderService implements ExpressionEvaluatorService {
	
	@Autowired
	ExpressionBuilder expressionBuilder;
	
	public String evaluate(String formula)
	{
		log.info("Enter evaluate method in ExpressionBuilderService");
		log.debug("formula " + formula);
		String finalResult = null;
		Optional<String> finalResultOptional = null;
		try {
			finalResult =  expressionBuilder.evaluate(formula);
			log.debug("Final Result" + finalResult);
			finalResultOptional = Optional.ofNullable(finalResult);
		}
		catch(Exception e)
		{
			log.error("Exception occurred " , e);
			finalResult = e.getLocalizedMessage();
			finalResultOptional = Optional.ofNullable(finalResult);
		}
		log.info("Exit evaluate method in ExpressionBuilderService");
		
		return finalResultOptional.isPresent()?finalResult:"Invalid Expression";
	}

}
