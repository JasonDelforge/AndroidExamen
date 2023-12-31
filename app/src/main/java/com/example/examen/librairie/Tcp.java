package com.example.examen.librairie;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Tcp
{
    public static Socket sClient =null;
    public static void buildSocket(String ipServeur,int portServeur) throws IOException{
        sClient=null;
        try {
            System.out.println("debut creation socket");
            sClient = new Socket(ipServeur,portServeur);
            System.out.println("Socket crée ");
        }
        catch (IOException e)
        {
            System.err.println("erreur de création de socket");
            e.printStackTrace();
            if (sClient !=null)
            {
                sClient.close();
            }
            throw e;
        }
    }
    public static String EchangeRequete(String requete) throws IOException
    {
        StringBuilder reponse = new StringBuilder();
        try {
            Tcp.EnvoieRequete(sClient,requete);
            System.out.println("requete envoyee ");
            System.out.println("on attend la reponse");
            int nombreLus = Tcp.ReceptionRequete(sClient,reponse);
            if(nombreLus < 0)
            {
                System.err.println("Erreur de Reception");
                sClient.close();
                System.exit(1);
            }
            if (nombreLus == 0)
            {
                System.out.println("Serveur arrêté, pas de réponse reçue...");
                sClient.close();
                System.exit(1);
            }
            return reponse.toString();
        } catch (IOException e)
        {
            System.err.println("Erreur lors de l'echange: "+ e.getMessage());
            sClient.close();
            System.exit(1);
            throw e;
        }
    }
    public static int EnvoieRequete(Socket socket,String data) throws IOException
    {
        int taille = data.length();
        if(taille > 200)
        {
            return -1;
        }
        String trame = data;
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(trame.getBytes(),0,trame.length());
        outputStream.flush();
        return taille;
    }
    public static int ReceptionRequete(Socket socket,StringBuilder data) throws IOException
    {
        boolean fini = false;
        int i = 0;
        char lu1,lu2;
        while (!fini)
        {
            int nombreLus = socket.getInputStream().read();
            if(nombreLus == -1)
            {
                return -1;
            }
            if(nombreLus == 0)
            {
                return i;
            }
            lu1 = (char) nombreLus;
            if (lu1 == '+')
            {
                nombreLus = socket.getInputStream().read();
                if(nombreLus == -1)
                {
                    return -1;
                }
                if (nombreLus == 0)
                {
                    return i;
                }
                lu2 = (char) nombreLus;
                if (lu2 == ')')
                {
                    fini = true;
                }
                else
                {
                    data.append(lu1);
                    data.append(lu2);
                    i +=2;
                }
            }
            else
            {
                data.append(lu1);
                i++;
            }
        }
        return i;
    }
}
