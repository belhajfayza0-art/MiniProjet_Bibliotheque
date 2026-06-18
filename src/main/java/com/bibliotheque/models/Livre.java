package com.bibliotheque.models;

import java.time.LocalDate;

public class Livre {
    // ===== ATTRIBUTS =====
    private int id;
    private String titre;
    private String auteur;
    private String isbn;
    private String categorie;
    private int annee;
    private LocalDate datePublication;
    private int nbPages;
    private double prix;
    private double note;
    private String etat;
    private boolean disponible;
    private String description;

    // ===== CONSTRUCTEURS =====
    public Livre() {}

    // CONSTRUCTEUR 1 : Pour votre interface (sans datePublication)
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
        this.datePublication = LocalDate.of(annee, 1, 1);
    }

    // CONSTRUCTEUR 2 : Pour votre interface (avec état)
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
        this.datePublication = LocalDate.of(annee, 1, 1);
    }

    // CONSTRUCTEUR 3 : Complet (pour votre interface)
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
        this.datePublication = LocalDate.of(annee, 1, 1);
    }

    // CONSTRUCTEUR 4 : Pour le DAO de votre coéquipière (AVEC datePublication et description)
    public Livre(int id, String titre, String auteur, String categorie,
                 int nbPages, double note, boolean disponible,
                 LocalDate datePublication, String description) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = "";
        this.categorie = categorie;
        this.nbPages = nbPages;
        this.prix = 0.0;
        this.note = note;
        this.etat = "Bon état";
        this.disponible = disponible;
        this.datePublication = datePublication;
        this.annee = datePublication != null ? datePublication.getYear() : 2024;
        this.description = description;
    }

    // CONSTRUCTEUR 5 : Avec tous les paramètres (le plus complet)
    public Livre(int id, String titre, String auteur, String isbn, String categorie,
                 LocalDate datePublication, int nbPages, double prix, double note,
                 String etat, boolean disponible, String description) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.categorie = categorie;
        this.datePublication = datePublication;
        this.annee = datePublication != null ? datePublication.getYear() : 2024;
        this.nbPages = nbPages;
        this.prix = prix;
        this.note = note;
        this.etat = etat;
        this.disponible = disponible;
        this.description = description;
    }

    // CONSTRUCTEUR 6 : Pour TestDAO (sans id, sans isbn, sans prix, sans etat)
    public Livre(String titre, String auteur, String categorie,
                 int nbPages, double note, boolean disponible,
                 LocalDate datePublication, String description) {
        this.id = 0;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = "";
        this.categorie = categorie;
        this.nbPages = nbPages;
        this.prix = 0.0;
        this.note = note;
        this.etat = "Bon état";
        this.disponible = disponible;
        this.datePublication = datePublication;
        this.annee = datePublication != null ? datePublication.getYear() : 2024;
        this.description = description;
    }

    // ===== GETTERS ET SETTERS =====
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
    public void setAnnee(int annee) {
        this.annee = annee;
        if (datePublication == null) {
            this.datePublication = LocalDate.of(annee, 1, 1);
        }
    }

    public LocalDate getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
        if (datePublication != null) {
            this.annee = datePublication.getYear();
        }
    }

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

    // ===== ALIAS POUR COMPATIBILITÉ AVEC LE DAO =====
    public int getIdLivre() { return id; }
    public void setIdLivre(int idLivre) { this.id = idLivre; }

    public String getNomAuteur() { return auteur; }
    public void setNomAuteur(String nomAuteur) { this.auteur = nomAuteur; }

    public int getNb_pages() { return nbPages; }
    public void setNb_pages(int nbPages) { this.nbPages = nbPages; }

    @Override
    public String toString() {
        return titre + " - " + auteur;
    }
}