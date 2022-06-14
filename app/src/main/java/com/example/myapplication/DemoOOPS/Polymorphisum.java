package com.example.myapplication.DemoOOPS;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class Polymorphisum extends Encapsulation {

    Button button;
    TextView txt;
    EditText number1,number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polymorphisum);

        button = findViewById(R.id.button);
        number1 = findViewById(R.id.edit1);
        number2 = findViewById(R.id.edit2);
        txt = findViewById(R.id.hello);




//        int n=3;
//        int m= 4;
//        int sum = n+m;

        button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              int num1 = Integer.parseInt(number1.getText().toString());
              //int num2 = Integer.parseInt(number2.getText().toString());
              double num2 = Double.parseDouble(number2.getText().toString());

              Logic l = new Logic();
              txt.setText(String.valueOf(l.sum(num1,num2)));

          }
      });
    }
}