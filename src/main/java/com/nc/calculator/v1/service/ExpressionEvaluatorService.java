package com.nc.calculator.v1.service;

import org.springframework.stereotype.Service;

@Service
public interface ExpressionEvaluatorService {

	String evaluate(String formula);
}
