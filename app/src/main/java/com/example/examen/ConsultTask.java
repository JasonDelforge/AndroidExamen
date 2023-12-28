package com.example.examen;

import android.os.AsyncTask;

import java.io.IOException;

public class ConsultTask extends AsyncTask<Void, Void, ArticleEnCours> {
    int position;

    public ConsultTask(int position) {
        this.position = position;
    }

    public ConsultTask() {
    }

    @Override
    protected ArticleEnCours doInBackground(Void... voids) {
        try {
            String requete = construireRequete("CONSULT", position);
            System.out.println(requete);
            String reponse = Tcp.EchangeRequete(requete);
            ArticleEnCours articleEnCours = parseReponse(reponse);
            return articleEnCours;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String construireRequete(String typeRequete, int position) {
        StringBuilder requete = new StringBuilder();
        requete.append(typeRequete).append("#").append(position).append("+)");
        System.out.println(requete);
        return requete.toString();
    }



    private ArticleEnCours parseReponse(String reponse) {
        // Supposons que votre réponse a le format suivant : "ID#INTITULE#PRIX#STOCK#IMAGE+)"
        String[] data = reponse.split("#");

        if (data.length >= 5 && reponse.endsWith("+)")) {
            int id = Integer.parseInt(data[0]);
            String intitule = data[1];
            float prix = Float.parseFloat(data[2]);
            int stock = Integer.parseInt(data[3]);
            // Retirez le caractère de fin "+)" de la chaîne de l'image
            String image = data[4].substring(0, data[4].length() - 2);

            return new ArticleEnCours(id, intitule, prix, stock, image);
        }

        return null; // Gestion d'une réponse incorrecte ou incomplète
    }
}