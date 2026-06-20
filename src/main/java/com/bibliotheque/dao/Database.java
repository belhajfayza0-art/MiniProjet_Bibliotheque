package com.bibliotheque.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque?serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";  // ← Vide pour WAMP

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion MySQL réussie !");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Driver MySQL non trouvé !");
                throw new SQLException("Driver MySQL non trouvé", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("✅ Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors de la fermeture : " + e.getMessage());
            }
        }
    }
}