package com.bibliotheque.dao;

import com.bibliotheque.models.Livre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    public void ajouter(Livre livre) throws SQLException {
        String sql = "INSERT INTO livre (titre, auteur, isbn, categorie, annee, prix, etat, disponible) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getIsbn());
            stmt.setString(4, livre.getCategorie());
            stmt.setInt(5, livre.getAnnee());
            stmt.setDouble(6, livre.getPrix());
            stmt.setString(7, livre.getEtat());
            stmt.setBoolean(8, livre.isDisponible());
            stmt.executeUpdate();
        }
    }

    public List<Livre> listerTous() throws SQLException {
        List<Livre> list = new ArrayList<>();
        String sql = "SELECT * FROM livre ORDER BY id";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("isbn"),
                        rs.getString("categorie"),
                        rs.getInt("annee"),
                        rs.getDouble("prix"),
                        rs.getString("etat"),
                        rs.getBoolean("disponible")
                ));
            }
        }
        return list;
    }

    public void modifier(Livre livre) throws SQLException {
        String sql = "UPDATE livre SET titre=?, auteur=?, isbn=?, categorie=?, annee=?, prix=?, etat=?, disponible=? WHERE id=?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getIsbn());
            stmt.setString(4, livre.getCategorie());
            stmt.setInt(5, livre.getAnnee());
            stmt.setDouble(6, livre.getPrix());
            stmt.setString(7, livre.getEtat());
            stmt.setBoolean(8, livre.isDisponible());
            stmt.setInt(9, livre.getId());
            stmt.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM livre WHERE id=?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Livre> rechercher(String motCle) throws SQLException {
        List<Livre> list = new ArrayList<>();
        String sql = "SELECT * FROM livre WHERE titre LIKE ? OR auteur LIKE ?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(sql)) {
            String recherche = "%" + motCle + "%";
            stmt.setString(1, recherche);
            stmt.setString(2, recherche);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Livre(
                            rs.getInt("id"),
                            rs.getString("titre"),
                            rs.getString("auteur"),
                            rs.getString("isbn"),
                            rs.getString("categorie"),
                            rs.getInt("annee"),
                            rs.getDouble("prix"),
                            rs.getString("etat"),
                            rs.getBoolean("disponible")
                    ));
                }
            }
        }
        return list;
    }
}