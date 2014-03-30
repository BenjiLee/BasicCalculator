package com.example.basiccalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//This comment was pull from git and added in windows.
	private MyMath math;
	String operators = "+-/*=";
	double input;
	boolean operatorSet = true;
	boolean numberSet = false;
	boolean ready = false;
	String rawInput = "";
	String last = "";
	TextView showResult;
	TextView showInput;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		math = new MyMath();
		showResult = (TextView) findViewById(R.id.TextView_input);
		showInput = (TextView) findViewById(R.id.TextView_equation);
	}

	public void btn1Clicked(View v) {
		insert("1");
	}

	public void btn2Clicked(View v) {
		insert("2");
	}

	public void btn3Clicked(View v) {
		insert("3");
	}

	public void btn4Clicked(View v) {
		insert("4");
	}

	public void btn5Clicked(View v) {
		insert("5");
	}

	public void btn6Clicked(View v) {
		insert("6");
	}

	public void btn7Clicked(View v) {
		insert("7");
	}

	public void btn8Clicked(View v) {
		insert("8");
	}

	public void btn9Clicked(View v) {
		insert("9");
	}

	public void btn0Clicked(View v) {
		insert("0");
	}

	public void btnDecimalClicked(View v) {
		insertDecimal(".");
	}

	public void btnPlusClicked(View v) {
		insertOperator("+");
	}

	public void btnMinusClicked(View v) {
		insertOperator("-");
	}

	public void btnDivideClicked(View v) {
		insertOperator("/");
	}

	public void btnMultiplyClicked(View v) {
		insertOperator("*");
	}

	public void btnDeleteClicked(View v) {
		delete();
	}

	public void btnEqualClicked(View v) {
		calculate();
	}

	public void btnClearClicked(View v) {
		clear();
	}

	public void delete() {
		if (!rawInput.equals("")) {
			rawInput = rawInput.substring(0, rawInput.length() - 1);
			showInput.setText(rawInput);
			if (rawInput.equals("")) {
				numberSet = false;
				operatorSet = true;
				ready = false;
			}
		}
	}

	private void clear() {
		rawInput = "";
		showResult.setText(rawInput);
		showInput.setText(rawInput);
		numberSet = false;
		operatorSet = true;
		ready = false;
	}

	private void insertOperator(String s) {
		if (!rawInput.equals("") && numberSet) {
			String last = rawInput.substring(rawInput.length() - 1);
			if (operators.indexOf(last) == -1)
			{
				rawInput = rawInput + s;
			}
			else {
				rawInput = rawInput.substring(0, rawInput.length() - 1) + s;
			}
			showInput.setText(rawInput);
			operatorSet = true;
			numberSet = false;
			ready = true;
		}
	}
	private void insertDecimal(String s) {
		if(operatorSet){
			rawInput = rawInput + s;
			operatorSet = false;
			showInput.setText(rawInput);
		}
		
	}

	private void insert(String s) {
		rawInput = rawInput + s;
		numberSet = true;
		showInput.setText(rawInput);
	}

	private void calculate() {
		if (numberSet && ready){
			String last = rawInput.substring(rawInput.length() - 1);
			if (operators.indexOf(last) == -1) {
				String result = math.calculate(rawInput);
				showResult.setText(result);
			}
		}
	}
}
