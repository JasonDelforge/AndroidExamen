package com.example.examen.classe;

public class ArticleEnCours
{
    private int id;
    private String intitule;
    private float prix;
    private int  stock;
    private String image;

    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public float getPrix() {
        return prix;
    }

    public int getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArticleEnCours(int id, String intitule, float prix, int stock, String image) {
        this.id = id;
        this.intitule = intitule;
        this.prix = prix;
        this.stock = stock;
        this.image = image;
    }

    public ArticleEnCours() {
    }
}
