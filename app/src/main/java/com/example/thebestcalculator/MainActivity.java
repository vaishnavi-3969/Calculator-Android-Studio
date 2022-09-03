package com.example.thebestcalculator;

import static java.lang.String.format;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;
public class MainActivity extends AppCompatActivity {
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(v -> {
            if(getString(R.string.display).equals(display.getText().toString())){
                display.setText("");
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos =  display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
           display.setText(strToAdd);
        }else {
            display.setText(format("%s%s%s", leftStr, strToAdd,rightStr));
            display.setSelection(cursorPos + 1);
        }
    }
    public void zeroBTN(View view){
//      updateText("0");
        display.append("0");
    }
    public void oneBTN(View view){
        display.append("1");
    }
    public void twoBTN(View view){
        display.append("2");
    }
    public void threeBTN(View view){
        display.append("3");
    }
    public void fourBTN(View view){
        display.append("4");
    }
    public void fiveBTN(View view){
        display.append("5");
    }
    public void sixBTN(View view){
        display.append("6");
    }
    public void sevenBTN(View view){
        display.append("7");
    }
    public void eightBTN(View view){
        display.append("8");
    }
    public void nineBTN(View view){
        display.append("9");
    }
    public void expBTN(View view){
        display.append("^");
    }
    public void addBTN(View view){
        display.append("+");
    }
    public void subtractBTN(View view){
        display.append("-");
    }
    public void clearBTN(View view){
//        updateText("");
        display.getText().clear();
    }
    public void multiplyBTN(View view){
        display.append("x");
    }
    public void divideBTN(View view){
        display.append("÷");
    }
    public void parBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openPar =0;
        int closedPar = 0;
        int textLen = display.getText().length();
        for(int i=0; i<cursorPos; i++){
            if(display.getText().toString().charAt(i) == '('){
                openPar +=1;
            }
            if(display.getText().toString().charAt(i) == ')'){
                closedPar +=1;
            }

        }
        if(openPar == closedPar || display.getText().toString().charAt(textLen - 1) == '('){
            updateText("(");
            display.setSelection(cursorPos+1);

        }else if(openPar > closedPar && display.getText().toString().charAt(textLen - 1) != '('){
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }
    public void plusMinusBTN(View view){
        display.append("-");
    }
    public void decimalBTN(View view){
        display.append(".");
    }
    public void equalBTN(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("x","*");
        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }
    public void backspaceBTN(View view){
      int cursorPos = display.getSelectionStart();
      int textLen = display.getText().length();
      if(cursorPos != 0 && textLen != 0){
          SpannableStringBuilder selection= (SpannableStringBuilder)display.getText();
          selection.replace(cursorPos - 1,cursorPos, "");
          display.setSelection(cursorPos-1);
      }
    }

}