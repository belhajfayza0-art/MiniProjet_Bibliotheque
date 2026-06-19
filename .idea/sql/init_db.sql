
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
                                                                                                                                     ('L''Étranger', 'Albert Camus', '978-2-07-036002-4', 'Roman', 1942, 184, 11.50, 4.2, 'Usagé', TRUE, 'Un roman philosophique incontournable.', '1942-05-19'),
                                                                                                                                     ('La Peste', 'Albert Camus', '978-2-07-036003-1', 'Roman', 1947, 320, 14.50, 4.3, 'Bon état', TRUE, 'Une réflexion sur la résistance humaine.', '1947-06-10'),
                                                                                                                                     ('Le Seigneur des Anneaux', 'J.R.R. Tolkien', '978-2-07-040851-1', 'Fantastique', 1954, 1200, 29.90, 4.9, 'Neuf', TRUE, 'La saga fantastique la plus célèbre.', '1954-07-29'),
                                                                                                                                     ('Dune', 'Frank Herbert', '978-2-07-040852-8', 'Science-fiction', 1965, 600, 22.00, 4.7, 'Bon état', TRUE, 'Un chef-d''œuvre de science-fiction.', '1965-08-01'),
                                                                                                                                     ('Le Nom de la Rose', 'Umberto Eco', '978-2-07-040853-5', 'Policier', 1980, 500, 18.50, 4.4, 'Usagé', FALSE, 'Un roman policier médiéval.', '1980-09-05'),
                                                                                                                                     ('Le Guide du voyageur galactique', 'Douglas Adams', '978-2-07-040854-2', 'Science-fiction', 1979, 250, 13.90, 4.1, 'Bon état', TRUE, 'Un classique de la science-fiction humoristique.', '1979-10-12');

-- 5.2 Insérer des emprunts
INSERT INTO emprunt (id_livre, nom_emprunteur, date_emprunt, date_retour_prevue, date_retour_reel, statut, remarques) VALUES
                                                                                                                          (1, 'Koutar Baladi', '2026-06-15', '2026-06-29', NULL, 'En cours', 'Premier emprunt'),
                                                                                                                          (2, 'Fayza Belhaj', '2026-06-10', '2026-06-24', NULL, 'En cours', 'À rendre bientôt'),
                                                                                                                          (3, 'Yassine Haddi', '2026-06-01', '2026-06-15', '2026-06-14', 'Terminé', 'Rendu à temps'),
                                                                                                                          (4, 'Sofia Rahou', '2026-06-05', '2026-06-19', NULL, 'En cours', 'En bonne condition'),
                                                                                                                          (7, 'Hamza Ait', '2026-06-02', '2026-06-16', '2026-06-18', 'Terminé', 'Rendu avec retard');

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