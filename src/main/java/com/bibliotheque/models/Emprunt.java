package com.bibliotheque.models;

import java.time.LocalDate;

public class Emprunt {
    private int id;
    private int idLivre;
    private String nomEmprunteur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private String statut; // "En cours" ou "Terminé"
    private String remarques;

    // 1. Constructeur par défaut
    public Emprunt() {}

    // 2. Constructeur sans ID (pour l'ajout)
    public Emprunt(int idLivre, String nomEmprunteur, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, String statut, String remarques) {
        this.idLivre = idLivre;
        this.nomEmprunteur = nomEmprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = statut;
        this.remarques = remarques;
    }

    // 3. Constructeur avec ID (pour la lecture depuis la BDD)
    public Emprunt(int id, int idLivre, String nomEmprunteur, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, String statut, String remarques) {
        this.id = id;
        this.idLivre = idLivre;
        this.nomEmprunteur = nomEmprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = statut;
        this.remarques = remarques;
    }

    // ===== GETTERS =====
    public int getId() {
        return id;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public String getNomEmprunteur() {
        return nomEmprunteur;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public String getStatut() {
        return statut;
    }

    public String getRemarques() {
        return remarques;
    }

    // ===== SETTERS =====
    public void setId(int id) {
        this.id = id;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public void setNomEmprunteur(String nomEmprunteur) {
        this.nomEmprunteur = nomEmprunteur;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }
}