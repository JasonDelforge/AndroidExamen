package com.example.examen.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examen.R;
import com.example.examen.classe.Achat;
import com.example.examen.classe.ArticleEnCours;
import com.example.examen.classe.Logout;
import com.example.examen.classe.PanierItems;
import com.example.examen.classe.PanierManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConsultActivity extends AppCompatActivity {
    Button suivant;
    Button precedent;
    TextView prix;
    TextView article;
    TextView stock;
    ImageView image;
    EditText quantiteText;
    Button logout;
    Button acheter;
    Button panier;
    int i = 1;
    private List<PanierItems> articlesAchetes = PanierManager.getInstance().getArticlesAchetes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        image = findViewById(R.id.imageView);
        article = findViewById(R.id.IdArticle);
        prix = findViewById(R.id.IdPrix);
        stock = findViewById(R.id.IdStock);
        quantiteText = findViewById(R.id.IdQuantite);
        logout = findViewById(R.id.IdLogout);
        suivant = findViewById(R.id.IdSuivant);
        precedent = findViewById(R.id.IdPrecedent);
        acheter = findViewById(R.id.IdAchat);
        panier = findViewById(R.id.IdPanier);

        // Initialisation de la première instance de ConsultTask
        createConsultTask(i);

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoutonSuivantClick();
            }
        });

        precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                createConsultTask(i);
                if (i == 1) {
                    try {
                        Toast.makeText(ConsultActivity.this, "Revenu au debut", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        acheter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantiteChoisis = quantiteText.getText().toString();

                int articleId = i;
                String articleNom = article.getText().toString();
                float articlePrix = Float.parseFloat(prix.getText().toString());
                int articleStock = Integer.parseInt(stock.getText().toString());

                articlesAchetes.add(new PanierItems(new ArticleEnCours(articleId, articleNom, articlePrix, articleStock, ""), Integer.parseInt(quantiteChoisis)));

                Toast.makeText(ConsultActivity.this, quantiteChoisis + " " + articleNom + " acheté(s)", Toast.LENGTH_SHORT).show();
                System.out.println(articlesAchetes);
                Achat achat = new Achat(quantiteChoisis, articleId);
                achat.execute();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bouton_Logout(v);
            }
        });

        panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultActivity.this, PanierActivity.class);
                System.out.println(articlesAchetes);
                intent.putExtra("achats", (Serializable) articlesAchetes);
                startActivity(intent);
            }
        });
    }


    private void createConsultTask(int index) {
        new ConsultTask(index) {
            @Override
            protected void onPostExecute(ArticleEnCours articleEnCours) {
                if (articleEnCours != null) {
                    updateInterface(articleEnCours);
                }
            }
        }.execute();
    }

    private void BoutonSuivantClick() {
        i++;
        createConsultTask(i);

        if (i == 21) {
            Toast.makeText(ConsultActivity.this, "Vous êtes à la fin", Toast.LENGTH_LONG).show();
        }
    }

    public void Bouton_Logout(View view) {
        Logout log = new Logout();
        log.execute();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateInterface(ArticleEnCours articleEnCours) {
        String imgRecue = articleEnCours.getImage();
        String nomImgSansExt = retireExtension(imgRecue);
        int imgID = getResources().getIdentifier(nomImgSansExt, "drawable", getPackageName());
        Drawable draw = getDrawable(imgID);
        image.setImageDrawable(draw);
        image.setVisibility(View.VISIBLE);
        article.setText(articleEnCours.getIntitule());
        prix.setText(String.valueOf(articleEnCours.getPrix()));
        stock.setText(String.valueOf(articleEnCours.getStock()));
    }

    private String retireExtension(String NomFichier) {
        if (NomFichier != null && NomFichier.contains(".")) {
            return NomFichier.substring(0, NomFichier.lastIndexOf('.'));
        }
        return NomFichier;
    }
}