package com.example.aklam.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText editPremiereNombre;
    EditText editSecondNombre;

    Button buttonCalculer;
    TextView textResultat;
    RadioGroup radioGroup;

    double resultat; //int
    char operateur ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPremiereNombre = findViewById(R.id.editPremiereNombre);
        editSecondNombre = findViewById(R.id.editSecondNombre);
        buttonCalculer = findViewById(R.id.buttonCalculer);
        textResultat = findViewById(R.id.textResultat);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radioGroup = findViewById(R.id.radioGroup);

        buttonCalculer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculerNombre();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.item1){
            Intent myintent = new Intent(MainActivity.this,item1_selection.class);
            startActivity(myintent);
            Log.d("TAG", "mon itent ");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    boolean verifierNombreSaisie(){

        if(editPremiereNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Le nombre 1 est obligatoire", Toast.LENGTH_LONG).show();

            // Place le curseur sur la zone de saisie du nombre 1
            editPremiereNombre.requestFocus();
            return false;
        }

        if(editSecondNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Le nombre 2 est obligatoire", Toast.LENGTH_LONG).show();

            // Place le curseur sur la zone de saisie du nombre 2
            editSecondNombre.requestFocus();
            return false;
        }

        return true;
    }

    public void calculerNombre(View view) {
        switch (view.getId()){

            case R.id.radio_addition:
                operateur = '+';
                break;

            case R.id.radio_soustraction:
                operateur = '-';
                break;

            case R.id.radio_multiplication:
                operateur = '*';
                break;

            case R.id.radio_division:
                operateur = '/';
                break;
        }
    }


    public void calculerNombre(){


        double nombre1 = Integer.parseInt(editPremiereNombre.getText().toString());
        double nombre2 = Integer.parseInt(editSecondNombre.getText().toString());
        nombre1 = Double.parseDouble(String.valueOf(nombre1));
        nombre2 = Double.parseDouble(String.valueOf(nombre2));

        if(operateur == '+') {
            resultat = nombre1 + nombre2;
        }
        if(operateur == '-') {
            resultat = nombre1 - nombre2;
        }
        if(operateur == '*') {
            resultat = nombre1 * nombre2;
        }
        if(operateur == '/') {
            if (nombre2 == 0) {
                Toast.makeText(this, "Impossible de faire ce calcul !", Toast.LENGTH_LONG).show();
            }else
                resultat = nombre1 / nombre2;
        }
        textResultat.setText(String.valueOf(resultat));
    }

}
