package com.example.aklam.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText et3;
    EditText et4;
    Button b;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b =  (Button) findViewById(R.id.button);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        tv = (TextView ) findViewById(R.id.textView5);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et3.getText().toString().isEmpty() || et4.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Remplissez obligatoirement les deux champs svp", Toast.LENGTH_LONG).show();
                }else {
                    //Toast.makeText(MainActivity.this, R.string.Erreur, Toast.LENGTH_SHORT).show();
                    int num1 = Integer.parseInt(et3.getText().toString());
                    int num2 = Integer.parseInt(et4.getText().toString());

                    int som = num1 + num2;

                    tv.setText(String.valueOf(som));
                }
                /*
                if (et3.getText().toString().length() == 0) {
                    et3.setText("0");
                }
                if (et4.getText().toString().length() == 0) {
                    et4.setText("0");
                }
                */



                };
        });

    }
}
