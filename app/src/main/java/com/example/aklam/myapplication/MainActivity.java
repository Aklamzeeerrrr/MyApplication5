package com.example.aklam.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText editPremiereNombre;
    EditText editSecondNombre;
    Button buttonCalculer;
    TextView textResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalculer = (Button) findViewById(R.id.buttonCalculer);
        editPremiereNombre = (EditText) findViewById(R.id.editPremiereNombre);
        editSecondNombre = (EditText) findViewById(R.id.editSecondNombre);
        textResultat = (TextView) findViewById(R.id.textResultat);

        buttonCalculer.setOnClickListener(calculerListener);

    }

    View.OnClickListener calculerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(verifierNombreSaisie()){
                calculerNombre();
            }
        }
    };

    void calculerNombre(){

        int nombre1 = Integer.parseInt(editPremiereNombre.getText().toString());
        int nombre2 = Integer.parseInt(editSecondNombre.getText().toString());

        int somme = nombre1 + nombre2;

        textResultat.setText(String.valueOf(somme));
    }

    boolean verifierNombreSaisie(){

        if(editPremiereNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Le nombre 1 est obligatoire", Toast.LENGTH_LONG).show();

            // Place le curseur sur la zone de saisie du nombre 1
            editPremiereNombre.requestFocus();
            return false;
        }

        if(editSecondNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Le nombre 1 est obligatoire", Toast.LENGTH_LONG).show();

            // Place le curseur sur la zone de saisie du nombre 2
            editSecondNombre.requestFocus();
            return false;
        }

        return true;
    }

}
