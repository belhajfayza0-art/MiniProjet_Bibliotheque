package com.bibliotheque.dao;

import com.bibliotheque.models.Livre;
import java.time.LocalDate;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        try {
            LivreDAO dao = new LivreDAO();

            // 1. Ajouter un livre
            Livre livre = new Livre(
                    "Le Petit Prince",
                    "Antoine de Saint-Exupéry",
                    "Roman",
                    96,
                    4.5,
                    true,
                    LocalDate.of(1943, 4, 6),
                    "Un classique de la littérature française."
            );
            dao.ajouter(livre);
            System.out.println("Livre ajouté avec succès !");

            // 2. Lister tous les livres
            List<Livre> livres = dao.listerTous();
            System.out.println(" Liste des livres en BDD :");
            for (Livre l : livres) {
                System.out.println(" - " + l.getTitre() + " (ID: " + l.getId() + ")");
            }

            // 3. Tester la recherche
            System.out.println(" Recherche 'Saint' : " + dao.rechercher("Saint").size() + " résultat(s)");

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}