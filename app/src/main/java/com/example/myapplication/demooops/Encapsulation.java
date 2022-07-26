package com.example.myapplication.demooops;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class Encapsulation extends AppCompatActivity {


   private Button btn;
    TextView txt;
    EditText name;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encapsulation);

        btn = findViewById(R.id.button);
        name = findViewById(R.id.edit1);
        email = findViewById(R.id.edit2);
        txt = findViewById(R.id.hello);

        EncapsulationLogic l = new EncapsulationLogic();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();

                l.setName(Name);
                l.setEmail(Email);

                String dname = l.getName();
                String demail = l.getEmail();


                if (dname.isEmpty() || demail.isEmpty()) {
                    txt.setText("Empty");
                    Toast.makeText(Encapsulation.this, "Enter Name And Email For Result!", Toast.LENGTH_SHORT).show();
                } else {
                    txt.setText(String.valueOf("Name :- " + dname + "\n Email :-" + demail));
                }
            }
        });
    }
}