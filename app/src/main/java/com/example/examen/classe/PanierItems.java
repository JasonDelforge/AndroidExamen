package com.example.examen.classe;

public class PanierItems extends ArticleEnCours
{
    private int quantite;
    public PanierItems(ArticleEnCours articleEnCours,int quantite)
    {
        super(articleEnCours.getId(),articleEnCours.getIntitule(),articleEnCours.getPrix(),articleEnCours.getStock(),articleEnCours.getImage());
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    @Override
    public String toString() {
        return "ID Article: " + getId() + ", Article: " + getIntitule() + ", Quantité: " + quantite;
    }

}
