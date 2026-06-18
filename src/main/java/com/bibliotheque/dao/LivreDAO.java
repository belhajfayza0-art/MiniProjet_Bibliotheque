package com.bibliotheque.dao;

import com.bibliotheque.models.Livre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    // 1. Ajouter un livre
    public void ajouter(Livre livre) throws SQLException {
        String sql = "INSERT INTO livre (titre, auteur, categorie, nb_pages, note, disponible, date_publication, description) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getCategorie());
            stmt.setInt(4, livre.getNbPages());
            stmt.setDouble(5, livre.getNote());
            stmt.setBoolean(6, livre.isDisponible());
            stmt.setDate(7, Date.valueOf(livre.getDatePublication()));
            stmt.setString(8, livre.getDescription());
            stmt.executeUpdate();
        }
    }

    // 2. Lister tous les livres
    public List<Livre> listerTous() throws SQLException {
        List<Livre> list = new ArrayList<>();
        String sql = "SELECT * FROM livre";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nb_pages"),
                        rs.getDouble("note"),
                        rs.getBoolean("disponible"),
                        rs.getDate("date_publication").toLocalDate(),
                        rs.getString("description")
                ));
            }
        }
        return list;
    }

    // 3. Modifier un livre
    public void modifier(Livre livre) throws SQLException {
        String sql = "UPDATE livre SET titre=?, auteur=?, categorie=?, nb_pages=?, note=?, disponible=?, date_publication=?, description=? WHERE id=?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getCategorie());
            stmt.setInt(4, livre.getNbPages());
            stmt.setDouble(5, livre.getNote());
            stmt.setBoolean(6, livre.isDisponible());
            stmt.setDate(7, Date.valueOf(livre.getDatePublication()));
            stmt.setString(8, livre.getDescription());
            stmt.setInt(9, livre.getId());
            stmt.executeUpdate();
        }
    }

    // 4. Supprimer un livre
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM livre WHERE id=?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // 5. Rechercher par mot-clé (titre ou auteur)
    public List<Livre> rechercher(String motCle) throws SQLException {
        List<Livre> list = new ArrayList<>();
        String sql = "SELECT * FROM livre WHERE titre LIKE ? OR auteur LIKE ?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, "%" + motCle + "%");
            stmt.setString(2, "%" + motCle + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Livre(
                            rs.getInt("id"),
                            rs.getString("titre"),
                            rs.getString("auteur"),
                            rs.getString("categorie"),
                            rs.getInt("nb_pages"),
                            rs.getDouble("note"),
                            rs.getBoolean("disponible"),
                            rs.getDate("date_publication").toLocalDate(),
                            rs.getString("description")
                    ));
                }
            }
        }
        return list;
    }
}