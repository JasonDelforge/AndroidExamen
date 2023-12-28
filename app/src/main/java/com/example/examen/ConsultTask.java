package com.example.examen;

import android.os.AsyncTask;

import java.io.IOException;

public class ConsultTask extends AsyncTask<Void,Void,Boolean>
{
    int position;
    public ConsultTask(int position) {
        this.position = position;
    }

    public ConsultTask() {
    }

    @Override
    protected Boolean doInBackground(Void... voids)
    {
        try {
            String requete = construireRequete("CONSULT",position);
            String reponse = Tcp.EchangeRequete(requete);
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    private String construireRequete(String typeRequete, int position) {
        StringBuilder requete = new StringBuilder();
        requete.append(typeRequete).append("#").append(position).append("#+)");
        System.out.println(requete);
        return requete.toString();
    }
}
