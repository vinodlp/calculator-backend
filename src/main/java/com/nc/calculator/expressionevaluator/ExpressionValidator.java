package com.nc.calculator.expressionevaluator;

import org.springframework.stereotype.Component;

@Component
public class ExpressionValidator {

	public static void validate(String expression) throws Exception
	{
		char[] expressionArray = expression.toCharArray();
		int countContinousDot = 0;
		long countOpenParanthesis = expression.chars().filter(ch -> ch == '(').count();
		long countCloseParanthesis = expression.chars().filter(ch -> ch == ')').count();
		
		if(countOpenParanthesis != countCloseParanthesis)
		{
			throw new Exception();
		}		
		for(char c: expressionArray)
		{
			  if (c >= '0' && c <= '9') {
				  countContinousDot = 0;
				  continue;
			  }
			  if(c=='.') {
				  countContinousDot++;
				  if(countContinousDot>1)
				  {
					  throw new Exception();
				  }
				  continue;
			  }
			  if(c=='(') {
				  countContinousDot = 0;
				  continue; 
			  }
			  if(c==')') {
				  countContinousDot = 0;
				  continue; 
			  }
			  if(c=='%') {
				  countContinousDot = 0;
				  continue; 
			  }
			  if (!Constants.operators.contains(c+"")) {
				  throw new Exception();
			  }
			  else{
				  countContinousDot = 0;
			  }
		}
	}
}
