package com.example.examen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultActivity extends AppCompatActivity {
    Button suivant;
    Button precedent;
    Button retour;
    TextView prix;
    TextView article;
    TextView stock;
    ImageView image;
    Spinner quantite;
    public ArticleEnCours articleEnCours = new ArticleEnCours();
    Button logout;

    int i = 1;
    private static ConsultActivity instance;

    public static ConsultActivity getInstance() {
        return instance;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        image = findViewById(R.id.imageView);
        article = findViewById(R.id.IdArticle);
        prix = findViewById(R.id.IdPrix);
        stock = findViewById(R.id.IdStock);
        quantite = findViewById(R.id.spinnerQuantite);
        logout = findViewById(R.id.IdLogout);
        ConsultTask consultTask = new ConsultTask(i);

        consultTask.execute();
    }


}