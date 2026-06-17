package com.bibliotheque.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque?serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = ""; // Laisse vide pour Wamp

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // TEST : Clique sur le triangle vert pour lancer
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            System.out.println("Connexion MySQL réussie !");
            conn.close();
        } catch (SQLException e) {
            System.err.println("Échec de connexion : " + e.getMessage());
        }
    }
}