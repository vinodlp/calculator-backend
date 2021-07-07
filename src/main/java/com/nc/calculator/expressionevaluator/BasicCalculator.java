package com.nc.calculator.expressionevaluator;

import org.springframework.stereotype.Component;

@Component
public class BasicCalculator {
	
	public double calculate(double d1,double d2, String operator)
	{
		
		double result = 0;
		switch(operator) {
		
		case "+":
			result = d1 + d2;
			break;
		case "-":
			result = d1 - d2;
			break;
		case "*":
			result = d1 * d2;
			break;
		case "/":
			result = d1 / d2;
			break;
		case "%":
			result = d1 % d2;
			break;			
		}
		
		return result;
		
		
	}
}
