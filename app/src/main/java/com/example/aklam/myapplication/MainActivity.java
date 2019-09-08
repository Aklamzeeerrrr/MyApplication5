package com.example.aklam.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.MediaStore;
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
import android.content.SharedPreferences;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    private final String PREF_FILE = "com.example.mysharedpreferences.PREF_FILE";
    private final String PREF_KEY = "MY_SAVE_DATA";
    private final String PREF_KEY1 = "MY_SAVE_DATA1";
    private final String PREF_KEY2 = "MY_SAVE_DATA2";
    private EditText mEditText;
    private String mDataString;
    private EditText mEditText1;
    private String mDataString1;
    private RadioGroup mEditText2;
    private String mDataString2;

    EditText editPremiereNombre;
    EditText editSecondNombre;

    Button buttonCalculer;
    TextView textResultat;
    RadioGroup radioGroup;

    RadioButton additionButton;
    RadioButton soustractionButton;
    RadioButton multiplicationButton;
    RadioButton divisionButton;

    int idButtonSauvegarde;

    double resultat; //int
    char operateur ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restaurer les données à partir de la SharedPreferences
        ReadSharedPreferences();
        // Trouver une vue qui a été identifiée par un ID du fichier XML
        mEditText = (EditText) findViewById(R.id.editPremiereNombre);
        mEditText1 = (EditText) findViewById(R.id.editSecondNombre);
        mEditText2 = (RadioGroup) findViewById(R.id.radioGroup);
        divisionButton =(RadioButton) findViewById(R.id.radio_division);
        additionButton =(RadioButton) findViewById(R.id.radio_addition);
        soustractionButton =(RadioButton) findViewById(R.id.radio_soustraction);
        multiplicationButton =(RadioButton) findViewById(R.id.radio_multiplication);
        // Afficher les données des EditText et du radioGroup
        mEditText.setText(mDataString);
        mEditText1.setText(mDataString1);
        mEditText2.check(idButtonSauvegarde);



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
                        if (verifierNombreSaisie() == true){
                            calculerNombre();
                        }
                        else{
                            return;
                        }

                    }
                }


        );


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        // Inflate le menu; Cela ajoute des items à la toolbar si ils sont presents.
        getMenuInflater().inflate(R.menu.main, menu);
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


    public void calculerNombre(View view) {
        switch (view.getId()){

            case R.id.radio_addition:
                operateur = '+';
                idButtonSauvegarde = additionButton.getId();
                break;


            case R.id.radio_soustraction:
                operateur = '-';
                idButtonSauvegarde = soustractionButton.getId();
                break;

            case R.id.radio_multiplication:
                operateur = '*';
                idButtonSauvegarde = multiplicationButton.getId();
                break;

            case R.id.radio_division:
                operateur = '/';
                idButtonSauvegarde = divisionButton.getId();
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

    @Override
    protected void onPause() {
        super.onPause();
// Lire les données entrées par l'utilisateur et l'enregister
        mDataString = mEditText.getText().toString();
        mDataString1 = mEditText1.getText().toString();
        mDataString2 = Integer.toString(idButtonSauvegarde);
//Enregistrer ces données dans le SharedPreferences
        SaveSharedPreferences(mDataString, mDataString1, mDataString2);
    }

    private void ReadSharedPreferences() {
// Recuperer et conserver le contenu du fichier de preference
        SharedPreferences preferences = getSharedPreferences(PREF_FILE,
                Context.MODE_PRIVATE);
// Récupérer les données à partir des  preferences
        mDataString = preferences.getString(PREF_KEY, "");
        mDataString1 = preferences.getString(PREF_KEY1, "");
        idButtonSauvegarde = Integer.parseInt(preferences.getString(PREF_KEY2, ""));
    }

    private void SaveSharedPreferences(String val, String val1, String val2) {
// Recuperer et conserver le contenu du fichier de preference
        SharedPreferences preferences = getSharedPreferences(PREF_FILE,
                Context.MODE_PRIVATE);
// Creer un nouvel editeur pour ces preferences
        SharedPreferences.Editor editor = preferences.edit();
// Définir la valeur des données dans l'editeur de preferences
        editor.putString(PREF_KEY, val);
        editor.putString(PREF_KEY1, val1);
        editor.putString(PREF_KEY2, val2);
// Utiliser la fonction commit()
        editor.commit();
    }

}
