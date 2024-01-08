package com.example.examen.classe;

import java.util.ArrayList;
import java.util.List;

//Classe Singleton
public class PanierManager {
    private static PanierManager instance;
    private List<PanierItems> articlesAchetes;

    private PanierManager() {
        articlesAchetes = new ArrayList<>();
    }

    public static PanierManager getInstance() {
        if (instance == null) {
            instance = new PanierManager();
        }
        return instance;
    }

    public List<PanierItems> getArticlesAchetes() {
        return articlesAchetes;
    }
}

