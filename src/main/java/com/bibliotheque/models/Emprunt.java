package com.bibliotheque.models;

import java.time.LocalDate;

public class Emprunt {
    private int id;
    private Livre livre;
    private String emprunteur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourReel;
    private String statut;
    private String remarques;

    public Emprunt() {}

    public Emprunt(int id, Livre livre, String emprunteur, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, String statut, String remarques) {
        this.id = id;
        this.livre = livre;
        this.emprunteur = emprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = statut;
        this.remarques = remarques;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
    public String getEmprunteur() { return emprunteur; }
    public void setEmprunteur(String emprunteur) { this.emprunteur = emprunteur; }
    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }
    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
    public LocalDate getDateRetourReel() { return dateRetourReel; }
    public void setDateRetourReel(LocalDate dateRetourReel) { this.dateRetourReel = dateRetourReel; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getRemarques() { return remarques; }
    public void setRemarques(String remarques) { this.remarques = remarques; }
    public String getTitreLivre() { return livre != null ? livre.getTitre() : ""; }
}