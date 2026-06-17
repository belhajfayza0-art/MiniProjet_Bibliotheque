package com.bibliotheque.dao;

import com.bibliotheque.models.Emprunt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    // 1. Ajouter un emprunt
    public void ajouter(Emprunt emprunt) throws SQLException {
        String sql = "INSERT INTO emprunt (id_livre, nom_emprunteur, date_emprunt, date_retour_prevue, statut, remarques) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, emprunt.getIdLivre());
            stmt.setString(2, emprunt.getNomEmprunteur());
            stmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            stmt.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
            stmt.setString(5, emprunt.getStatut());
            stmt.setString(6, emprunt.getRemarques());
            stmt.executeUpdate();
        }
    }

    // 2. Lister tous les emprunts
    public List<Emprunt> listerTous() throws SQLException {
        List<Emprunt> list = new ArrayList<>();
        String sql = "SELECT * FROM emprunt";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getString("nom_emprunteur"),
                        rs.getDate("date_emprunt").toLocalDate(),
                        rs.getDate("date_retour_prevue").toLocalDate(),
                        rs.getString("statut"),
                        rs.getString("remarques")
                ));
            }
        }
        return list;
    }

    // 3. Modifier un emprunt (par exemple, changer le statut)
    public void modifier(Emprunt emprunt) throws SQLException {
        String sql = "UPDATE emprunt SET id_livre=?, nom_emprunteur=?, date_emprunt=?, date_retour_prevue=?, statut=?, remarques=? WHERE id=?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, emprunt.getIdLivre());
            stmt.setString(2, emprunt.getNomEmprunteur());
            stmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            stmt.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
            stmt.setString(5, emprunt.getStatut());
            stmt.setString(6, emprunt.getRemarques());
            stmt.setInt(7, emprunt.getId());
            stmt.executeUpdate();
        }
    }

    // 4. Supprimer un emprunt
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM emprunt WHERE id=?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // 5. Rechercher les emprunts par nom d'emprunteur
    public List<Emprunt> rechercherParNom(String nom) throws SQLException {
        List<Emprunt> list = new ArrayList<>();
        String sql = "SELECT * FROM emprunt WHERE nom_emprunteur LIKE ?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, "%" + nom + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Emprunt(
                            rs.getInt("id"),
                            rs.getInt("id_livre"),
                            rs.getString("nom_emprunteur"),
                            rs.getDate("date_emprunt").toLocalDate(),
                            rs.getDate("date_retour_prevue").toLocalDate(),
                            rs.getString("statut"),
                            rs.getString("remarques")
                    ));
                }
            }
        }
        return list;
    }
}