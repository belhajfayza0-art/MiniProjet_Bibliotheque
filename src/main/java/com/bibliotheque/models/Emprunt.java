package com.bibliotheque.models;

import java.time.LocalDate;

public class Emprunt {
    // ===== ATTRIBUTS =====
    private int id;
    private Livre livre;
    private int idLivre;  // Pour le DAO
    private String emprunteur;
    private String nomEmprunteur;  // Pour le DAO
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourReel;
    private String statut;
    private String remarques;

    // ===== CONSTRUCTEURS =====
    public Emprunt() {}

    // CONSTRUCTEUR 1 : Pour votre interface (avec Livre)
    public Emprunt(int id, Livre livre, String emprunteur, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, String statut, String remarques) {
        this.id = id;
        this.livre = livre;
        this.idLivre = livre != null ? livre.getId() : 0;
        this.emprunteur = emprunteur;
        this.nomEmprunteur = emprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = statut;
        this.remarques = remarques;
        this.dateRetourReel = null;
    }

    // CONSTRUCTEUR 2 : Pour le DAO de votre coéquipière (AVEC idLivre)
    public Emprunt(int id, int idLivre, String nomEmprunteur, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, String statut, String remarques) {
        this.id = id;
        this.idLivre = idLivre;
        this.nomEmprunteur = nomEmprunteur;
        this.emprunteur = nomEmprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = statut;
        this.remarques = remarques;
        this.livre = null;
        this.dateRetourReel = null;
    }

    // CONSTRUCTEUR 3 : Simple pour le DAO (sans idLivre)
    public Emprunt(int id, String nomEmprunteur, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, String statut, String remarques) {
        this.id = id;
        this.nomEmprunteur = nomEmprunteur;
        this.emprunteur = nomEmprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = statut;
        this.remarques = remarques;
        this.livre = null;
        this.idLivre = 0;
        this.dateRetourReel = null;
    }

    // CONSTRUCTEUR 4 : Complet avec dateRetourReel
    public Emprunt(int id, int idLivre, String nomEmprunteur, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, LocalDate dateRetourReel,
                   String statut, String remarques) {
        this.id = id;
        this.idLivre = idLivre;
        this.nomEmprunteur = nomEmprunteur;
        this.emprunteur = nomEmprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourReel = dateRetourReel;
        this.statut = statut;
        this.remarques = remarques;
        this.livre = null;
    }

    // ===== GETTERS ET SETTERS =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) {
        this.livre = livre;
        if (livre != null) {
            this.idLivre = livre.getId();
        }
    }

    public int getIdLivre() { return idLivre; }
    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
        if (livre == null) {
            livre = new Livre();
        }
        livre.setId(idLivre);
    }

    public String getEmprunteur() { return emprunteur; }
    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur;
        this.nomEmprunteur = emprunteur;
    }

    public String getNomEmprunteur() { return nomEmprunteur != null ? nomEmprunteur : emprunteur; }
    public void setNomEmprunteur(String nomEmprunteur) {
        this.nomEmprunteur = nomEmprunteur;
        this.emprunteur = nomEmprunteur;
    }

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

    public String getTitreLivre() {
        return livre != null ? livre.getTitre() : "";
    }

    // ===== ALIAS POUR COMPATIBILITÉ =====
    public int getIdEmprunt() { return id; }
    public void setIdEmprunt(int idEmprunt) { this.id = idEmprunt; }

    public String getDateEmpruntString() {
        return dateEmprunt != null ? dateEmprunt.toString() : "";
    }

    public String getDateRetourPrevueString() {
        return dateRetourPrevue != null ? dateRetourPrevue.toString() : "";
    }

    public String getEstEnRetard() {
        if ("En cours".equals(statut) && dateRetourPrevue != null) {
            return LocalDate.now().isAfter(dateRetourPrevue) ? "⚠️ Oui" : "Non";
        }
        return "N/A";
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", idLivre=" + idLivre +
                ", emprunteur='" + emprunteur + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}