package com.example.examen.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examen.R;

public class PanierActivity extends AppCompatActivity
{
    GridView gridPanier;
    Button supprimer;
    Button payer;
    Button SupprimerPanier;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
    }
}
