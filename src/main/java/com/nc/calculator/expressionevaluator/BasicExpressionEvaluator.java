package com.nc.calculator.expressionevaluator;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicExpressionEvaluator {
	
	@Autowired
	BasicCalculator basicCaluclator;

	public String evaluate(String expression) throws Exception
	{
		String result = "";
		String[] digits = new String[2];
		
		String operator =Constants.operators.stream().filter(op -> expression.contains(op))
		.collect(Collectors.joining());
		
		if(operator.equals("+-"))
		{
			operator = "+";
		}
		if(operator.equals("--"))
		{
			operator = "-";
		}
		if(operator.equals("*-"))
		{
			operator = "*";
		}
		if(operator.equals("/-"))
		{
			operator = "/";
		}		
		if(operator.length()==0)
		{
			throw new Exception();
		}
		digits = expression.split(operator.equals("*")?"\\*":operator.equals("+")?"\\+":operator);
		
		if(operator.equals("-") && digits[0].equals(""))
		{
			if(digits.length>2)
			{
				digits[0] = "-" + digits[1];
				digits[1] = digits[2];
			}else {
			
			return expression;
			}
		}		
		Double doubleVal = basicCaluclator.calculate(convertToNumber(digits[0]),convertToNumber(digits[1]),operator);
		result = doubleVal.toString();
		return result;
	}

	private double convertToNumber(String digit) {
		double result = 0;
		double divBy100 = 1;
		if(digit.contains("%"))
		{
			digit = digit.replace("%", "");
			divBy100 = 100;
			
		}
		
		result = Double.parseDouble(digit)/divBy100;
		
		return result;
	}
}
