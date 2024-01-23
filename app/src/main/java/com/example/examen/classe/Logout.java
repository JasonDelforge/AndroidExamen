package com.example.examen.classe;

import android.os.AsyncTask;

import com.example.examen.librairie.Tcp;

import java.io.IOException;

public class Logout extends AsyncTask<Void, Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        String requete = "LOGOUT#" + "+)";
        String reponse;
            try {
                reponse = Tcp.EchangeRequete(requete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        return null;
    }

    @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }