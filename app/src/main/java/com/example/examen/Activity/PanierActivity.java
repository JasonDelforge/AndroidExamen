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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int articleId = extras.getInt("article_id");
            String articleQuantite = extras.getString("article_quantite");
            String articleNom = extras.getString("article_nom");
            String articlePrix = extras.getString("article_prix");
            String articleStock = extras.getString("article_stock");
            if (articleId != -1 && articleQuantite != null && articleNom != null && articlePrix != null && articleStock != null) {
                PanierItems nouvelArticle = new PanierItems(new ArticleEnCours(articleId, articleNom, Float.parseFloat(articlePrix), Integer.parseInt(articleStock), ""), Integer.parseInt(articleQuantite));
                ajouterAuPanier(nouvelArticle);
            }
        }

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoutonRetourClick();
            }
        });
    }

    private void BoutonRetourClick() {
        Intent intent = new Intent(PanierActivity.this, ConsultActivity.class);
        startActivity(intent);
    }

    private void ajouterAuPanier(PanierItems article) {
        panierItemsList.add(article);
        panierAdapters.notifyDataSetChanged();
        Toast.makeText(this, "Article ajouté au panier", Toast.LENGTH_SHORT).show();
    }
}
