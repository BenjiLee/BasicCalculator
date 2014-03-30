package com.example.basiccalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private MyMath math;
	double input;
	String rawInput = "";
	TextView showResult;
	TextView showInput;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		math = new MyMath();
		showResult = (TextView)findViewById(R.id.TextView_input);
		showInput = (TextView)findViewById(R.id.TextView_equation);
	}
	
	public void btn1Clicked(View v){insert("1");}
	public void btn2Clicked(View v){insert("2");}
	public void btn3Clicked(View v){insert("3");}
	public void btn4Clicked(View v){insert("4");}
	public void btn5Clicked(View v){insert("5");}
	public void btn6Clicked(View v){insert("6");}
	public void btn7Clicked(View v){insert("7");}
	public void btn8Clicked(View v){insert("8");}
	public void btn9Clicked(View v){insert("9");}
	public void btn0Clicked(View v){insert("0");}
	public void btnDecimalClicked(View v){insert(".");} 
	public void btnPlusClicked(View v){insert("+");}
	public void btnMinusClicked(View v){insert("-");}
	public void btnDivideClicked(View v){insert("/");}
	public void btnMultiplyClicked(View v){insert("*");}
	public void btnDeleteClicked(View v){delete();}
	public void btnEqualClicked(View v){calculate();}
	public void btnClearClicked(View v){clear();}//TODO decimal is a special case. 
	
	public void delete() {
		if (!rawInput.equals("")){
			rawInput = rawInput.substring(0, rawInput.length() - 1);
			showInput.setText(rawInput);
		}
	}

	private void clear() {
		rawInput = "";
		showResult.setText(rawInput);
		showInput.setText(rawInput);
	}
	private void insert(String s) {
		rawInput = rawInput + s;
		showInput.setText(rawInput);
		
	}
	private void calculate() {
		if (!rawInput.equals("")){
			String result = math.calculate(rawInput);
			showResult.setText(result);
			rawInput = "";
		}
	}
}

