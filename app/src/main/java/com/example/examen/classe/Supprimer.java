package com.example.examen.classe;

import android.os.AsyncTask;

import com.example.examen.Activity.ConsultTask;
import com.example.examen.librairie.Tcp;

import java.io.IOException;

public class Supprimer extends AsyncTask<String, Void, Boolean> {

    private int id;

    public Supprimer(int id) {
        this.id = id;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            String requete = construireRequeteCancel(String.valueOf(id));
            String reponse = Tcp.EchangeRequete(requete);
            String[] partieReponse = reponse.split("#");
            if (partieReponse[1] == "OUI"){
                return true;
            }
            else return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String construireRequeteCancel(String id) {
        return "CANCEL#" + id + "+)";
    }

    @Override
    protected void onPostExecute(Boolean valid)
    {
        if(valid)
        {
            ConsultTask consultTask = new ConsultTask(id);
            consultTask.execute();
        }
    }
}
