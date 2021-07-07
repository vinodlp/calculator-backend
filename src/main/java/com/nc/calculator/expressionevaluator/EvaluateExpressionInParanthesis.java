package com.nc.calculator.expressionevaluator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvaluateExpressionInParanthesis {

	@Autowired
	EvaluateHighPrecedenceOperator evaluateHighPrecedenceOperator;
	
	public String evaluate(String expression) throws Exception
	{
		while(true)
		{
			int startIndex = expression.lastIndexOf("(");
			if(startIndex==-1)
			{
				break;
			}
			expression = extractSmallestExpressionInParanthesis(expression);
		}
		
		return expression;
	}
	
	public String extractSmallestExpressionInParanthesis(String expression) throws Exception
	{
		int startIndex = expression.lastIndexOf("(");
		int endIndex = expression.indexOf(")", startIndex);
		String evalutedRes = "";
		if(expression.substring(startIndex+1,endIndex).length()>0) {
		 evalutedRes = evaluateHighPrecedenceOperator.evaluate(expression.substring(startIndex+1,endIndex));
		}
		expression =  expression.substring(0,startIndex) + evalutedRes + expression.substring(endIndex+1,expression.length());
		ExpressionValidator.validate(expression);
		return expression; 
		 

	}
}
