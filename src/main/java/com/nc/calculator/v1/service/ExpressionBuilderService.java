package com.nc.calculator.v1.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Slf4j
@Service
public class ExpressionBuilderService implements ExpressionEvaluatorService {
	
	public String evaluate(String formula)
	{
		log.info("Enter evaluate method in ExpressionBuilderService");
		log.debug("formula " + formula);
		String finalResult = null;
		Optional<String> finalResultOptional = null;
		try {
			Expression expression = new ExpressionBuilder(formula).build();
			double result = expression.evaluate();
			finalResult = Double.toString(result);
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
