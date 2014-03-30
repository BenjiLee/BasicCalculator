package com.example.basiccalculator;

import java.util.Stack;

public class MyMath {

	String operators = "+-/*=";
	Stack<String> lifo = new Stack<String>();

	public String calculate(String equation) {
		System.out.println("Infix:" + equation);
		String postfix = infixToPostfix(equation);
		System.out.println("Postfix:" + postfix);
		String result = calculatePostfix(postfix);
		return result;
	}

	private String calculatePostfix(String p) {
		String result = "";
		String held = "";
		String second = "";

		for (int i = 0; i < p.length(); i++) {
			String current = Character.toString(p.charAt(i));
			System.out.println("mewow:"+current + " held: " + held);
			if (!current.equals(",") && !isOperator(current)) {
				held = held + current;
			} else if (current.equals(",")) {
				lifo.push(held);
				held = "";
			} else {
				if (held.equals("")){
					second = lifo.pop().toString();
				}
				else {
					second = held;
				}
				System.out.println("OPERATION:"+lifo.peek().toString()+current+second);
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

		for (int i = 0; i < length; i++) {
			String target = Character.toString(infix.charAt(i));
			if (isOperator(target)) {
				result = result + ",";
				if (lifo.empty()) {
					lifo.push(target);
				} 
				else {
					String topOperator = lifo.peek().toString();
					while (!lifo.empty() && operators.indexOf(topOperator) > 1) {
						result = result + lifo.pop();
						if (!lifo.empty()){
							topOperator = lifo.peek().toString();
						}
					}
					if (operators.indexOf(target) > 1 ){
						lifo.push(target);
					}
					else{
						while (!lifo.empty()) {
							result = result + lifo.pop();
						}
						lifo.push(target);
					}
				}
			} else {
				result = result + target;
			}
		}
		while (!lifo.empty()) {
			result = result + lifo.pop();
		}
		return result;
	}

	private boolean isOperator(String search) {
		if (operators.indexOf(search) != -1) {
			return true;
		}
		return false;
	}

	private String equate(String a, String operation, String b) {
		double left = Double.parseDouble(a);
		double right = Double.parseDouble(b);
		if (operation.equals("+")) {
			return String.valueOf(left + right);
		} else if (operation.equals("-")) {
			return String.valueOf(left - right);
		} else if (operation.equals("*")) {
			return String.valueOf(left * right);
		} else if (operation.equals("/")) {
			return String.valueOf(left / right);
		} else {
			return "error";
		}
	}

}
