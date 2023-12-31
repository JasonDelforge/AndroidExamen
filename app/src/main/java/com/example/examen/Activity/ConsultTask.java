package com.example.examen.Activity;

import android.os.AsyncTask;

import com.example.examen.classe.ArticleEnCours;
import com.example.examen.librairie.Tcp;

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
            ArticleEnCours articleEnCours = parseResponse(reponse);
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
    private ArticleEnCours parseResponse(String response) {
        ArticleEnCours articleEnCours = new ArticleEnCours();
        String[] parts = response.split("#");
        if (parts.length >= 5) {
            articleEnCours.setIntitule(parts[2]);
            articleEnCours.setPrix(Float.parseFloat(parts[3]));
            articleEnCours.setStock((int) Float.parseFloat(parts[4]));
            articleEnCours.setImage(parts[5].trim());
        }
        if (!articleEnCours.getImage().equals(normalizeImageName(articleEnCours.getImage()))) {
            String image = normalizeImageName(articleEnCours.getImage());
            articleEnCours.setImage(image);
        }
        return articleEnCours;
    }
    private String normalizeImageName(String imageName) {
        // Si le nom du fichier est "PommesDeTerre", le normaliser
        if ("pommesDeTerre.jpg".equalsIgnoreCase(imageName)) {
            return "pommesdeterre.jpg";
        } else {
            return imageName;
        }
    }

}