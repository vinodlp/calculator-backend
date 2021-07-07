package com.nc.calculator.expressionevaluator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvaluateHighPrecedenceOperator {

	@Autowired
	BasicExpressionEvaluator basicExpressionEvaluator;
	
	public String evaluate(String expression) throws Exception {
		while(true) {
		
		int highPrecidenceOperatorIndex = findFirstHighPrecedenceOperationIndex(expression);
		if(highPrecidenceOperatorIndex==-1)
		{
			break;
		}
		if(validateNumber(expression)) {
			break;
		}
		expression = extractSmallestExpression(expression,highPrecidenceOperatorIndex);
		System.out.println(expression);
		}
		return expression;
	}
	
	public String extractSmallestExpression(String expression,int opIndex) throws Exception
	{
		char[] expArray = expression.toCharArray();
		int startIndex = -1;
		int endIndex = -1;
		int i=opIndex-1;
		for(;i>=-1;i--) {
			
			try {
				if(expArray[i]=='.' || expArray[i]=='%' || (i==0 && expArray[i]=='-'))
				{
					continue;
				}
				Double.parseDouble(expArray[i]+"");
			}
			catch(Exception e) {
				if(i==opIndex-1) {
					//error
				}
				else {
					startIndex = i;
					break;
				}
			}
			
		}
		
		//if(startIndex==-1 && i<=0)
		//{
		//	startIndex = 0;
		//}
		i=opIndex+1;
		for(;i<expArray.length;i++) {
			
			try {
				if(expArray[i]=='.' || expArray[i]=='%')
				{
					continue;
				}
				
				Double.parseDouble(expArray[i]+"");
			}
			catch(Exception e) {
				if(i==opIndex-1) {
					//error
				}
				else {
					if(!isNumber(expArray[i-1]+""))
					{
						if(expArray[i]=='-')
						{
							continue;
						}
					}
					endIndex = i;
					break;
				}
			}
			
		}
		if(endIndex==-1 && i>=expArray.length)
		{
			endIndex = expArray.length;
		}
		
		 String evalutedRes = basicExpressionEvaluator.evaluate(expression.substring(startIndex+1,endIndex));
		
		 expression =  expression.substring(0,startIndex+1) + evalutedRes + expression.substring(endIndex,expArray.length);
		
		return  expression;
		
	}
	
	public boolean isNumber(String exp)
	{
		boolean result = false;
		try {
			Double.parseDouble(exp);
			result = true;
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	
	private boolean validateNumber(String expression) {
		boolean result = false;
		try {
			Double.parseDouble(expression);
			result= true;
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	
	public int findFirstHighPrecedenceOperationIndex(String expression)
	{
		for(String op:Constants.operators)
		{
			if(expression.contains(op))
			{
				if(expression.indexOf(op)==0)
				{
					if(expression.indexOf(op, 1)>-1) {
						return expression.indexOf(op, 1);
					}
				}
				return expression.indexOf(op);
			}
		}
		return -1;
	}
}
