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
import com.example.examen.classe.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PanierActivity extends AppCompatActivity{
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

        gridPanier.setChoiceMode(GridView.CHOICE_MODE_SINGLE); // Activer la sélection d'un seul article
        gridPanier.setSelected(true);

        List<PanierItems> articlesAchetes = (List<PanierItems>) getIntent().getSerializableExtra("achats");

        ajouterAuPanier(articlesAchetes);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PanierActivity.this, ConsultActivity.class);
                intent.putExtra("panier", (Serializable) panierItemsList);
                startActivity(intent);
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = gridPanier.getCheckedItemPosition();
                System.out.println(pos);
                if (pos != GridView.INVALID_POSITION) {
                    Supprimer supprimer = new Supprimer(pos);
                    supprimer.execute();

                    panierItemsList.remove(pos);
                    panierAdapters.notifyDataSetChanged();
                    Toast.makeText(PanierActivity.this, "Article supprimé du panier", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PanierActivity.this, "Sélectionnez un article à supprimer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SupprimerPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupprimerPanier supprimerPanier = new SupprimerPanier();
                supprimerPanier.execute();

                // Supprimer tout le contenu du panier
                panierItemsList.clear();
                panierAdapters.notifyDataSetChanged();
                Toast.makeText(PanierActivity.this, "Panier vidé", Toast.LENGTH_SHORT).show();
            }
        });

        payer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirmer confirmer = new Confirmer();
                confirmer.execute();

                panierItemsList.clear();
                panierAdapters.notifyDataSetChanged();
                Toast.makeText(PanierActivity.this, "Commande payée", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ajouterAuPanier(List<PanierItems> articlesAchetes) {
        panierItemsList.addAll(articlesAchetes);
        panierAdapters.notifyDataSetChanged();
        Toast.makeText(this, "Articles ajoutés au panier", Toast.LENGTH_SHORT).show();
    }
}
