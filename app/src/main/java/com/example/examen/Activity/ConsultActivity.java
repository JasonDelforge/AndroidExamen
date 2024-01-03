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
        ConsultTask consultTask = new ConsultTask(i) {
            @Override
            protected void onPostExecute(ArticleEnCours articleEnCours) {
                if (articleEnCours != null) {
                    updateInterface(articleEnCours);
                }
            }
        };
        consultTask.execute();

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
                new ConsultTask(i) {
                    @Override
                    protected void onPostExecute(ArticleEnCours articleEnCours) {
                        if (articleEnCours != null) {
                            updateInterface(articleEnCours);
                        }
                    }
                }.execute();
                if(i==1)
                {
                    try {
                        Toast.makeText(ConsultActivity.class.newInstance(),"Revenu au debut",Toast.LENGTH_LONG).show();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        acheter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quatiteChoisis = (String) quantiteText.getText().toString();
                Achat achat = new Achat(quatiteChoisis,i);
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
               Intent intent = new Intent(ConsultActivity.this,PanierActivity.class);
               startActivity((intent));

            }
        });

    }

    private void BoutonSuivantClick() {
        i++;
        new ConsultTask(i) {
            @Override
            protected void onPostExecute(ArticleEnCours articleEnCours) {
                if (articleEnCours != null) {
                    updateInterface(articleEnCours);
                }
            }
        }.execute();

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
