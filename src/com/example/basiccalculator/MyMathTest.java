package com.example.basiccalculator;

import junit.framework.TestCase;

public class MyMathTest extends TestCase {
	private MyMath math = new MyMath();
	
	
	  public void testCalculate() {
		  String result = math.calculate("8");
		  System.out.print(result);
	    }
	 
//	  public void testIndex(){
//		  Boolean result = math.isOperator("l"); //set to public to test
//		  System.out.print(result);
//	  }
}
