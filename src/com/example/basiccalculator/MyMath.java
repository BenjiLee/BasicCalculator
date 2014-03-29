package com.example.basiccalculator;

import java.util.Stack;

public class MyMath {

	String operators = "+-/*=";
	Stack lifo = new Stack();

	
	public String calculate(String equation){
		String postfix = infixToPostfix(equation);
		System.out.println(postfix);
		String result = calculatePostfix(postfix);
		return result;
	}
	
	private String calculatePostfix(String p){
		String result = "";
		int count = p.length() - p.replace(".", "").length();
		String held = "";
		String expression;
		String second = "";
		
		for (int i = 0; i < p.length(); i++) {
			String current = Character.toString(p.charAt(i));
			if (!current.equals(",") && !isOperator(current)){
				held = held + current;
			}
			else if (current.equals(",")){
				lifo.push(held);
				held = "";
			}
			else {
				System.out.println("OPERATION:"+lifo.peek().toString());
				second = lifo.pop().toString();
				held = equate(lifo.pop().toString(), current, second);
				if (held == "error") {
					return "error";
				}
				lifo.push(held);
				held = "";
			}
		}
		result = lifo.pop().toString();
				
		return result;
	}
	
	private String infixToPostfix(String infix) {
		String result = "";
		int length = infix.length();
		
		for (int i = 0; i < length; i++){
			String target = Character.toString(infix.charAt(i));
			if (isOperator(target)){
				result = result + ",";
				if (lifo.empty()){
					lifo.push(target);
				}
				else {
					while (!lifo.empty()) {
						String topOperator = lifo.peek().toString();
						if (canPop(target, topOperator)) {
							result = result + lifo.pop();
							if(lifo.empty()){
								lifo.push(target);
								break;
							}
						}
						else {
							lifo.push(target);
							break;
						}
					}	
				}
			}
			else {
				result = result +  target;
			}
		}
		if(!lifo.empty())
		{
			result = result + "," + lifo.pop();
		}
		return result;
	}
	
	private boolean isOperator(String search){
		if (operators.indexOf(search) != -1){
			return true;
		}
		return false;
	}
			
	private boolean canPop(String operand, String topOperator) {
		if (operators.indexOf(topOperator) > 1){
			return true;
		}
		else if (operators.indexOf(operand) >1) {
			return false;
		}
		return true;
	}
	
	private String equate(String a, String operation, String b) {
		double left = Double.parseDouble(a);
		double right = Double.parseDouble(b);
		if (operation.equals("+")){ return String.valueOf(left + right);}
		else if (operation.equals("-")){ return String.valueOf(left - right);}
		else if (operation.equals("*")){ return String.valueOf(left * right);}
		else if (operation.equals("/")) { return String.valueOf(left / right);}
		else { return "error"; }
	}
	
	
}
