package com.example.examen.classe;

import android.os.AsyncTask;

import com.example.examen.librairie.Tcp;

import java.io.IOException;

public class Confirmer extends AsyncTask<String, Void, Boolean> {
    public Confirmer() {
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            String requete = construireRequeteConfirm();
            String reponse = Tcp.EchangeRequete(requete);
            String[] partieReponse = reponse.split("#");
            if (partieReponse[1] == "CONFIRMERALL"){
                return true;
            }
            else return false;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private String construireRequeteConfirm() {
        return "CONFIRMER" + "+)";
    }
}
