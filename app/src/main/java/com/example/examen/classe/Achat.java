package com.example.examen.classe;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.examen.Activity.ConsultActivity;
import com.example.examen.Activity.ConsultTask;
import com.example.examen.librairie.Tcp;

public class Achat extends AsyncTask<String, Void, Boolean> {
    private String quantite;
    private int id;

    public Achat(String quantite,int id) {
        this.quantite = quantite;
        this.id = id;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            if (Integer.parseInt(quantite) <= 0) {
                return false;
            }
            String requete = construireRequeteAchat(String.valueOf(id),quantite);
            String reponse = Tcp.EchangeRequete(requete);
            String[] partieReponse = reponse.split("#");
            if(Integer.parseInt(partieReponse[1])!=-1)
            {
                if(partieReponse[2].equals("0"))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private String construireRequeteAchat(String id, String quantite) {
        return "ACHAT#" + id + "#" + quantite + "+)";
    }
    @Override
    protected void onPostExecute(Boolean valid)
    {
        if(valid)
        {
            /*try {
                Toast.makeText(ConsultActivity.class.newInstance(),"Article ajouté",Toast.LENGTH_LONG).show();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }*/
            ConsultTask consultTask = new ConsultTask(id);
            consultTask.execute();
        }
    }
}
