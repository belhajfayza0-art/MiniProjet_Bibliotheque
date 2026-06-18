package com.bibliotheque.models;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String isbn;
    private String categorie;
    private int annee;
    private int nbPages;
    private double prix;
    private double note;
    private String etat;
    private boolean disponible;
    private String description;

    public Livre() {}

    // CONSTRUCTEUR 1 : Basique (sans état, sans pages, sans note)
    public Livre(int id, String titre, String auteur, String isbn, String categorie,
                 int annee, double prix, boolean disponible) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.categorie = categorie;
        this.annee = annee;
        this.nbPages = 0;
        this.prix = prix;
        this.note = 0.0;
        this.etat = "Bon état";
        this.disponible = disponible;
        this.description = "";
    }

    // CONSTRUCTEUR 2 : Complet (avec état)
    public Livre(int id, String titre, String auteur, String isbn, String categorie,
                 int annee, int nbPages, double prix, double note, String etat, boolean disponible) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.categorie = categorie;
        this.annee = annee;
        this.nbPages = nbPages;
        this.prix = prix;
        this.note = note;
        this.etat = etat;
        this.disponible = disponible;
        this.description = "";
    }

    // CONSTRUCTEUR 3 : Avec état mais sans pages et note
    public Livre(int id, String titre, String auteur, String isbn, String categorie,
                 int annee, double prix, String etat, boolean disponible) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.categorie = categorie;
        this.annee = annee;
        this.nbPages = 0;
        this.prix = prix;
        this.note = 0.0;
        this.etat = etat;
        this.disponible = disponible;
        this.description = "";
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public int getNbPages() { return nbPages; }
    public void setNbPages(int nbPages) { this.nbPages = nbPages; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public double getNote() { return note; }
    public void setNote(double note) { this.note = note; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return titre + " - " + auteur;
    }
}