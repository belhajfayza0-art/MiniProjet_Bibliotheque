package com.bibliotheque.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.bibliotheque.models.Livre;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainController {

    // ========== LIVRES ==========
    @FXML private TextField titreField, auteurField, isbnField;
    @FXML private TextArea descriptionArea;
    @FXML private ComboBox<String> categorieCombo;
    @FXML private Spinner<Integer> anneeSpinner;
    @FXML private Slider prixSlider;
    @FXML private Label prixLabel;
    @FXML private CheckBox disponibleCheck;
    @FXML private RadioButton neufRadio, bonRadio, usageRadio;
    @FXML private ToggleGroup etatGroup;
    @FXML private DatePicker datePublicationPicker;
    @FXML private TableView<Livre> livreTableView;
    @FXML private TableColumn<Livre, Integer> livreIdCol;
    @FXML private TableColumn<Livre, String> livreTitreCol, livreAuteurCol, livreIsbnCol, livreCategorieCol;
    @FXML private TableColumn<Livre, Integer> livreAnneeCol;
    @FXML private TableColumn<Livre, Double> livrePrixCol;
    @FXML private TableColumn<Livre, Boolean> livreDisponibleCol;

    // ========== STATISTIQUES ==========
    @FXML private Label totalLivresLabel, livresDisponiblesLabel, livresEmpruntesLabel, noteMoyenneLabel;
    @FXML private ProgressIndicator disponibiliteIndicator;
    @FXML private Label statusLabel;

    private ObservableList<Livre> livresList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configuration du spinner
        anneeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2025, 2024));

        // Configuration du slider
        prixSlider.valueProperty().addListener((obs, old, val) ->
                prixLabel.setText(String.format("%.2f €", val.doubleValue()))
        );

        // Configuration du toggle group
        etatGroup = new ToggleGroup();
        neufRadio.setToggleGroup(etatGroup);
        bonRadio.setToggleGroup(etatGroup);
        usageRadio.setToggleGroup(etatGroup);

        // ✅ AJOUTER LES CATÉGORIES ICI (dans le code, pas dans le FXML)
        categorieCombo.getItems().addAll(
                "Roman",
                "Science-fiction",
                "Policier",
                "Scolaire",
                "Informatique",
                "Histoire",
                "Fantastique",
                "Jeunesse"
        );

        // Configuration du tableau
        livreIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        livreTitreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        livreAuteurCol.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        livreIsbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        livreCategorieCol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        livreAnneeCol.setCellValueFactory(new PropertyValueFactory<>("annee"));
        livrePrixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        livreDisponibleCol.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        // Données de test
        chargerDonneesTest();
        mettreAJourStatistiques();

        statusLabel.setText("✅ Application prête");
    }

    private void chargerDonneesTest() {
        livresList.addAll(
                new Livre(1, "Le Petit Prince", "Saint-Exupéry", "978-2-07-040850-4",
                        "Roman", 1943, 12.50, true),
                new Livre(2, "1984", "George Orwell", "978-2-07-036822-8",
                        "Science-fiction", 1949, 15.90, false),
                new Livre(3, "L'Étranger", "Albert Camus", "978-2-07-036002-4",
                        "Roman", 1942, 11.50, true)
        );
        livreTableView.setItems(livresList);
    }

    private void mettreAJourStatistiques() {
        int total = livresList.size();
        long disponibles = livresList.stream().filter(Livre::isDisponible).count();
        long empruntes = total - disponibles;
        double noteMoyenne = 4.5; // Valeur par défaut

        totalLivresLabel.setText(String.valueOf(total));
        livresDisponiblesLabel.setText(String.valueOf(disponibles));
        livresEmpruntesLabel.setText(String.valueOf(empruntes));
        noteMoyenneLabel.setText(String.format("%.1f", noteMoyenne));

        double taux = total > 0 ? (double) disponibles / total : 0;
        disponibiliteIndicator.setProgress(taux);
    }

    @FXML
    public void ajouterLivre(ActionEvent event) {
        if (titreField.getText().isEmpty() || auteurField.getText().isEmpty()) {
            afficherAlert("Erreur", "Veuillez remplir tous les champs obligatoires");
            return;
        }

        Livre livre = new Livre(
                livresList.size() + 1,
                titreField.getText(),
                auteurField.getText(),
                isbnField.getText(),
                categorieCombo.getValue() != null ? categorieCombo.getValue() : "Non classé",
                anneeSpinner.getValue(),
                prixSlider.getValue(),
                disponibleCheck.isSelected()
        );

        livresList.add(livre);
        reinitialiserFormulaireLivre(null);
        mettreAJourStatistiques();
        afficherAlert("Succès", "Livre ajouté avec succès !");
        statusLabel.setText("✅ Livre ajouté : " + livre.getTitre());
    }

    @FXML
    public void modifierLivre(ActionEvent event) {
        Livre selected = livreTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            afficherAlert("Attention", "Veuillez sélectionner un livre à modifier");
            return;
        }

        if (titreField.getText().isEmpty() || auteurField.getText().isEmpty()) {
            afficherAlert("Erreur", "Veuillez remplir tous les champs obligatoires");
            return;
        }

        selected.setTitre(titreField.getText());
        selected.setAuteur(auteurField.getText());
        selected.setIsbn(isbnField.getText());
        selected.setCategorie(categorieCombo.getValue() != null ? categorieCombo.getValue() : "Non classé");
        selected.setAnnee(anneeSpinner.getValue());
        selected.setPrix(prixSlider.getValue());
        selected.setDisponible(disponibleCheck.isSelected());

        livreTableView.refresh();
        reinitialiserFormulaireLivre(null);
        mettreAJourStatistiques();
        afficherAlert("Succès", "Livre modifié avec succès !");
        statusLabel.setText("✅ Livre modifié : " + selected.getTitre());
    }

    @FXML
    public void supprimerLivre(ActionEvent event) {
        Livre selected = livreTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            afficherAlert("Attention", "Veuillez sélectionner un livre à supprimer");
            return;
        }

        livresList.remove(selected);
        mettreAJourStatistiques();
        reinitialiserFormulaireLivre(null);
        afficherAlert("Succès", "Livre supprimé avec succès !");
        statusLabel.setText("🗑️ Livre supprimé : " + selected.getTitre());
    }

    @FXML
    public void reinitialiserFormulaireLivre(ActionEvent event) {
        titreField.clear();
        auteurField.clear();
        isbnField.clear();
        descriptionArea.clear();
        categorieCombo.getSelectionModel().clearSelection();
        anneeSpinner.getValueFactory().setValue(2024);
        prixSlider.setValue(20);
        prixLabel.setText("20.00 €");
        disponibleCheck.setSelected(true);
        bonRadio.setSelected(true);
        datePublicationPicker.setValue(null);
        livreTableView.getSelectionModel().clearSelection();
        statusLabel.setText("✅ Formulaire réinitialisé");
    }

    @FXML
    public void exporterLivresCSV(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter les livres en CSV");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );
        fileChooser.setInitialFileName("bibliotheque_" + java.time.LocalDate.now() + ".csv");

        Stage stage = (Stage) statusLabel.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Titre,Auteur,ISBN,Catégorie,Année,Prix,Disponible\n");
                for (Livre livre : livresList) {
                    writer.write(String.format("%s,%s,%s,%s,%d,%.2f,%s\n",
                            livre.getTitre(),
                            livre.getAuteur(),
                            livre.getIsbn(),
                            livre.getCategorie(),
                            livre.getAnnee(),
                            livre.getPrix(),
                            livre.isDisponible() ? "Oui" : "Non"
                    ));
                }
                afficherAlert("Succès", "Export CSV réussi !\nFichier : " + file.getName());
                statusLabel.setText("📤 Exporté vers " + file.getName());
            } catch (IOException e) {
                afficherAlert("Erreur", "Erreur lors de l'export : " + e.getMessage());
            }
        }
    }

    @FXML
    public void rafraichirStatistiques(ActionEvent event) {
        mettreAJourStatistiques();
        statusLabel.setText("✅ Statistiques mises à jour");
        afficherAlert("Info", "Statistiques rafraîchies !");
    }

    @FXML
    public void quitterApplication(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void afficherAPropos(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("À propos");
        alert.setHeaderText("📚 Gestion de Bibliothèque");
        alert.setContentText("Application JavaFX - ENSAO GI3\n" +
                "Développé par [Vos Noms]\n\n" +
                "Version 1.0 - 2025/2026");
        alert.showAndWait();
    }

    private void afficherAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}