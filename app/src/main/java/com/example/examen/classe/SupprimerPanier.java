package com.example.examen.classe;

import android.os.AsyncTask;

import com.example.examen.librairie.Tcp;

import java.io.IOException;

public class SupprimerPanier extends AsyncTask<String, Void, Boolean> {

    public SupprimerPanier() {
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            String requete = construireRequeteCancelall();
            String reponse = Tcp.EchangeRequete(requete);
            String[] partieReponse = reponse.split("#");
            if (partieReponse[1] == "OUIALL"){
                return true;
            }
            else return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String construireRequeteCancelall() {
        return "CANCELALL" + "+)";
    }
    /*@Override
    protected void onPostExecute(Boolean valid)
    {
        if(valid)
        {
            ConsultTask consultTask = new ConsultTask(id);
            consultTask.execute();
        }
    }*/
}
