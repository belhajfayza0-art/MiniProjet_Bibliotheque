package com.bibliotheque.models;

import java.time.LocalDate;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String categorie;
    private int nbPages;
    private double note;
    private boolean disponible;
    private LocalDate datePublication;
    private String description;

    // Constructeur sans ID (pour l'ajout)
    public Livre(String titre, String auteur, String categorie, int nbPages,
                 double note, boolean disponible, LocalDate datePublication, String description) {
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nbPages = nbPages;
        this.note = note;
        this.disponible = disponible;
        this.datePublication = datePublication;
        this.description = description;
    }

    // Constructeur avec ID (pour la lecture)
    public Livre(int id, String titre, String auteur, String categorie, int nbPages,
                 double note, boolean disponible, LocalDate datePublication, String description) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nbPages = nbPages;
        this.note = note;
        this.disponible = disponible;
        this.datePublication = datePublication;
        this.description = description;
    }

    // Constructeur par défaut
    public Livre() {}

    // === GETTERS ===
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getCategorie() { return categorie; }
    public int getNbPages() { return nbPages; }
    public double getNote() { return note; }
    public boolean isDisponible() { return disponible; }
    public LocalDate getDatePublication() { return datePublication; }
    public String getDescription() { return description; }

    // === SETTERS ===
    public void setId(int id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public void setNbPages(int nbPages) { this.nbPages = nbPages; }
    public void setNote(double note) { this.note = note; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setDatePublication(LocalDate datePublication) { this.datePublication = datePublication; }
    public void setDescription(String description) { this.description = description; }
}