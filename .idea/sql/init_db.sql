
CREATE DATABASE IF NOT EXISTS bibliotheque;
USE bibliotheque;


-- 2. Supprimer les tables si elles existent (pour réinitialiser)
DROP TABLE IF EXISTS emprunt;
DROP TABLE IF EXISTS livre;

-- 3. Créer la table livre

CREATE TABLE livre (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       titre VARCHAR(255) NOT NULL,
                       auteur VARCHAR(255) NOT NULL,
                       isbn VARCHAR(20),
                       categorie VARCHAR(100),
                       annee INT,
                       nb_pages INT,
                       prix DECIMAL(10,2),
                       note DECIMAL(3,2),
                       etat VARCHAR(50),
                       disponible BOOLEAN DEFAULT TRUE,
                       description TEXT,
                       date_publication DATE
);

-- 4. Créer la table emprunt (avec clé étrangère)

CREATE TABLE emprunt (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         id_livre INT NOT NULL,
                         nom_emprunteur VARCHAR(255) NOT NULL,
                         date_emprunt DATE NOT NULL,
                         date_retour_prevue DATE NOT NULL,
                         date_retour_reel DATE,
                         statut VARCHAR(50) DEFAULT 'En cours',
                         remarques TEXT,
                         FOREIGN KEY (id_livre) REFERENCES livre(id) ON DELETE CASCADE
);

-- 5. Insérer des données de test
-- 5.1 Insérer des livres
INSERT INTO livre (titre, auteur, isbn, categorie, annee, nb_pages, prix, note, etat, disponible, description, date_publication) VALUES
                                                                                                                                     ('Le Petit Prince', 'Antoine de Saint-Exupéry', '978-2-07-040850-4', 'Roman', 1943, 96, 12.50, 4.5, 'Bon état', TRUE, 'Un classique de la littérature française.', '1943-04-06'),
                                                                                                                                     ('1984', 'George Orwell', '978-2-07-036822-8', 'Science-fiction', 1949, 328, 15.90, 4.8, 'Neuf', FALSE, 'Une œuvre visionnaire sur la dystopie.', '1949-06-08'),
                                                                                                                                     ('L''Étranger', 'Albert Camus', '978-2-07-036002-4', 'Roman', 1942, 184, 11.50, 4.2, 'Usagé', TRUE, 'Un roman philosophique incontournable.', '1942-05-19');

-- 5.2 Insérer des emprunts
INSERT INTO emprunt (id_livre, nom_emprunteur, date_emprunt, date_retour_prevue, date_retour_reel, statut, remarques) VALUES
                                                                                                                          (1, 'Koutar Baladi', '2026-06-15', '2026-06-29', NULL, 'En cours', 'Premier emprunt'),
                                                                                                                          (2, 'Fayza Belhaj', '2026-06-10', '2026-06-24', NULL, 'En cours', 'À rendre bientôt');


-- ============================================================
-- 6. Vérification des données insérées
-- ============================================================
SELECT '=== LIVRES ===' AS '';
SELECT id, titre, auteur, disponible FROM livre;

SELECT '=== EMPRUNTS ===' AS '';
SELECT e.id, l.titre AS livre, e.nom_emprunteur, e.date_emprunt, e.statut
FROM emprunt e
         JOIN livre l ON e.id_livre = l.id;

-- ============================================================
-- 7. Fin du script
-- ============================================================
SELECT '✅ Base de données initialisée avec succès !' AS 'Statut';