package com.example.examen.Activity;

import android.os.AsyncTask;

import com.example.examen.librairie.Tcp;

import java.io.IOException;
import java.net.Socket;

public class SocketAsynchrone extends AsyncTask<Void, Void, Boolean> {
    private Socket socket;
    private String login, mdp;
    private Boolean nouveau;

    public SocketAsynchrone(String login, String mdp, Boolean nouveau) {
        this.login = login;
        this.mdp = mdp;
        this.nouveau = nouveau;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            String ip = "192.168.204.130";
            int port = 50000;
            if(Tcp.sClient == null || Tcp.sClient.isClosed()) {
                Tcp.buildSocket(ip, port);
            }
            String requete = construireRequete("LOGIN", login, mdp, nouveau ? "NC" : "PNC");
            System.out.println(requete);
            String reponse = Tcp.EchangeRequete(requete);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String construireRequete(String typeRequete, String... params) {
        StringBuilder requete = new StringBuilder();
        requete.append(typeRequete).append("#");
        System.out.println(requete);
        for (String param : params) {
            requete.append(param).append("#");
        }
        requete.deleteCharAt(requete.length() - 1);  // Supprimer le dernier caractère '#'
        requete.append("+)");
        return requete.toString();
    }
}