package com.example.aklam.myapplication;

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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    // Pour identifier de maniere unique ta preférence. Il est possible que 2 applications utilisent
    // La meme préference pour travailler
    private final String PACKAGE_NAME = "com.example.aklam.myapplication";

    private final String PREF_FILE_NAME = PACKAGE_NAME + ".PREF_FILE_NAME";
    private final String PREF_NOMBRE_1 = PACKAGE_NAME + "PREF_NOMBRE_1";
    private final String PREF_NOMBRE_2 = PACKAGE_NAME + "PREF_NOMBRE_2";
    private final String PREF_OPERATEUR = PACKAGE_NAME + "PREF_OPERATEUR";

    private String mNombre1;
    private String mNombre2;
    private String mOperateur;

    EditText editPremiereNombre;
    EditText editSecondNombre;

    Button buttonCalculer;
    TextView textResultat;
    RadioGroup radioGroupOperateur;

    int idRadioOperateur;

    double resultat; //int
    char operateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPremiereNombre = findViewById(R.id.editPremiereNombre);
        editSecondNombre = findViewById(R.id.editSecondNombre);
        buttonCalculer = findViewById(R.id.buttonCalculer);
        textResultat = findViewById(R.id.textResultat);

        // Radio group operateur, lui seul suffit pour gerer les radioButtons
        radioGroupOperateur = findViewById(R.id.radioGroupOperateur);

        // Restaurer les données à partir de la SharedPreferences
        afficherDerniereOperation();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonCalculer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (verifierNombreSaisie()) {
                            calculerNombre();
                        } else {
                            return;
                        }
                    }
                }
        );


    }

    boolean verifierNombreSaisie() {

        if (editPremiereNombre.getText().toString().isEmpty()) {
            Toast.makeText(this, "Le nombre 1 est obligatoire", Toast.LENGTH_LONG).show();
            editPremiereNombre.requestFocus();
            return false;
        }

        if (editSecondNombre.getText().toString().isEmpty()) {
            Toast.makeText(this, "Le nombre 2 est obligatoire", Toast.LENGTH_LONG).show();
            editSecondNombre.requestFocus();
            return false;
        }

        // Verifier aussi si un operateur a été selectionné
        if(radioGroupOperateur.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Veuillez selectionner un opérateur", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent myintent = new Intent(MainActivity.this, AproposActivity.class);
            startActivity(myintent);
            Log.d("TAG", "mon itent ");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void recupererSelectionOperateur() {

        // Tu peux recuperer l'id du radioButton selectionné dans le radioGroup
        // Pas besoin de declarer tous les radios buttons dans ton code
        idRadioOperateur = radioGroupOperateur.getCheckedRadioButtonId();

        switch (radioGroupOperateur.getCheckedRadioButtonId()) {

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


    public void calculerNombre() {

        recupererSelectionOperateur();

        double nombre1 = Double.parseDouble(editPremiereNombre.getText().toString());
        double nombre2 = Double.parseDouble(editSecondNombre.getText().toString());

        if (operateur == '+') {
            resultat = nombre1 + nombre2;
        }
        if (operateur == '-') {
            resultat = nombre1 - nombre2;
        }
        if (operateur == '*') {
            resultat = nombre1 * nombre2;
        }
        if (operateur == '/') {
            if (nombre2 == 0) {
                Toast.makeText(this, "Impossible de faire ce calcul !", Toast.LENGTH_LONG).show();
            } else
                resultat = nombre1 / nombre2;
        }
        textResultat.setText(String.valueOf(resultat));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Partie révisée
        mNombre1 = editPremiereNombre.getText().toString();
        mNombre2 = editSecondNombre.getText().toString();
        saveSharedPreferences(mNombre1, mNombre2, idRadioOperateur);
    }

    private void afficherDerniereOperation() {

        SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME,
                Context.MODE_PRIVATE);

        mNombre1 = preferences.getString(PREF_NOMBRE_1, "");
        mNombre2 = preferences.getString(PREF_NOMBRE_2, "");

        // Tu peux sauvegarder differents types de donneés avec la préférences
        // pas besoin de la recuperer en string et faire une conversion après
        idRadioOperateur = preferences.getInt(PREF_OPERATEUR, -1);

        // Afficher les données des EditText et du radioGroupOperateur
        editPremiereNombre.setText(mNombre1);
        editSecondNombre.setText(mNombre2);
        radioGroupOperateur.check(idRadioOperateur);

    }

    private void saveSharedPreferences(String nombre1, String nombre2, int operateur) {

        SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PREF_NOMBRE_1, nombre1);
        editor.putString(PREF_NOMBRE_2, nombre2);

        // Tu enregistres l'id de l'operateur en int
        editor.putInt(PREF_OPERATEUR, operateur);
        editor.commit();
    }

}
