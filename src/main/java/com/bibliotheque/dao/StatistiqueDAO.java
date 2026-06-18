package com.bibliotheque.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StatistiqueDAO {

    public int getTotalLivres() throws SQLException {
        String sql = "SELECT COUNT(*) FROM livre";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public int getLivresEmpruntes() throws SQLException {
        String sql = "SELECT COUNT(*) FROM livre WHERE disponible = FALSE";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public double getNoteMoyenne() throws SQLException {
        String sql = "SELECT AVG(note) FROM livre";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }

    public Map<String, Integer> getRepartitionParCategorie() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT categorie, COUNT(*) FROM livre GROUP BY categorie";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        }
        return map;
    }
}