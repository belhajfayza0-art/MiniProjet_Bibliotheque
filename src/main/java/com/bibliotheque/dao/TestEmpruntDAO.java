package com.bibliotheque.dao;

import com.bibliotheque.models.Emprunt;
import java.time.LocalDate;
import java.util.List;

public class TestEmpruntDAO {
    public static void main(String[] args) {
        try {
            EmpruntDAO dao = new EmpruntDAO();

            // Ajouter un emprunt (assure-toi qu'un livre avec id=1 existe)
            Emprunt emprunt = new Emprunt(
                    1, // id du livre
                    "Alice Martin",
                    LocalDate.now(),
                    LocalDate.now().plusDays(14),
                    "En cours",
                    "À rendre avant la fin du mois"
            );
            dao.ajouter(emprunt);
            System.out.println("Emprunt ajouté !");

            // Lister tous les emprunts
            List<Emprunt> list = dao.listerTous();
            System.out.println("📚 Liste des emprunts : " + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}