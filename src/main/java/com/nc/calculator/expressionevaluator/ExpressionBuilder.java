package com.nc.calculator.expressionevaluator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpressionBuilder {
	
	@Autowired
	EvaluateExpressionInParanthesis evaluateExpressionInParanthesis;
	
	@Autowired
	EvaluateHighPrecedenceOperator evaluateHighPrecedenceOperator;
	
	public String evaluate(String expression) throws Exception {
		ExpressionValidator.validate(expression);		
		expression = evaluateExpressionInParanthesis.evaluate(expression);
		expression = evaluateHighPrecedenceOperator.evaluate(expression);		
		System.out.println(expression);		
		return expression;
	}

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
