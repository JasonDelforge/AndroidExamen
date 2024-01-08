package com.example.examen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examen.R;
import com.example.examen.classe.ArticleEnCours;
import com.example.examen.classe.PanierItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PanierActivity extends AppCompatActivity {
    GridView gridPanier;
    Button supprimer;
    Button payer;
    Button SupprimerPanier;
    List<PanierItems> panierItemsList;
    Button retour;
    ArrayAdapter<PanierItems> panierAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        payer = findViewById(R.id.IdPayer);
        SupprimerPanier = findViewById(R.id.IdSupprimerPanier);
        supprimer = findViewById(R.id.IdSupprimer);
        gridPanier = findViewById(R.id.IdView);
        retour = findViewById(R.id.IdRetour);

        panierItemsList = new ArrayList<>();
        panierAdapters = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, panierItemsList);
        gridPanier.setAdapter(panierAdapters);

        List<PanierItems> articlesAchetes = (List<PanierItems>) getIntent().getSerializableExtra("achats");

        ajouterAuPanier(articlesAchetes);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoutonRetourClick();
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = gridPanier.getCheckedItemPosition();
                System.out.println(pos);
            }
        });
    }

    private void BoutonRetourClick() {
        Intent intent = new Intent(PanierActivity.this, ConsultActivity.class);
        startActivity(intent);
    }

    private void ajouterAuPanier(List<PanierItems> articlesAchetes) {
        panierItemsList.addAll(articlesAchetes);
        panierAdapters.notifyDataSetChanged();
        Toast.makeText(this, "Articles ajoutés au panier", Toast.LENGTH_SHORT).show();
    }
}
