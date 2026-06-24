package restaurant.main;

import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import restaurant.model.personnel.Cuisinier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import restaurant.model.personnel.cuisine.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import restaurant.model.service.Service;
import restaurant.model.plat.Plat;
import restaurant.exception.ServiceSurchargeException;
import restaurant.model.Brigade;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class App extends Application{

    public void start(Stage stage){
    stage.setTitle("Restauration");


    TableView<Cuisinier> tableau = new TableView();
    TableColumn<Cuisinier, String> nom = new TableColumn<>("Nom");
    TableColumn<Cuisinier, String> poste = new TableColumn<>("Poste");
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    poste.setCellValueFactory(new PropertyValueFactory<>("poste"));

    tableau.getColumns().addAll(nom, poste);

        //Création de tableau
    ObservableList<Cuisinier> membres = FXCollections.observableArrayList();
        membres.add(new ChefExecutif("Morel", "Lucas", "Chef de cuisine"));
        membres.add(new SousChef("Vuillemin", "Emma", "Sous Cheffe de Cuisine"));
        membres.add(new ChefDePartie("Girard", "Nathan", "Chef de Partie pour les sauces"));
        membres.add(new ChefDePartie("Perret", "Chloé", "Chef de Partie pour les pâtisseries"));
        membres.add(new Commis("Marchand", "Hugo", "Entrées"));
        membres.add(new Commis("Favre", "Sarah", "Desserts (avec Chloé)"));
        membres.add(new Commis("Piguet", "Laura", "Viandes"));

        tableau.setItems(membres);

        //BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(tableau);
        Scene scene = new Scene(root, 900,600);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());


        //VBox et HBox
        Label label = new Label();
        label.setText("KitchenFlow - Tableau de bord");
        ListView<String> listePlats = new ListView<String>();

        ObservableList<String> plats = FXCollections.observableArrayList(
                "Lasagne - EN_ATTENTE",
                "Crêpe au fromage - EN_COURS"
        );
        listePlats.setItems(plats);

        VBox vbox = new VBox(10, label, listePlats);
        root.setRight(vbox);

        //Création de button
        Button btnTraiter = new Button("Traiter commande");
        Button btnAjouter = new Button("Ajouter un plat");
        Button btnConfirmer = new Button("Ajouter membre");

        HBox hbox = new HBox(10, btnTraiter, btnAjouter);
        root.setBottom(hbox);
        hbox.setPadding(new Insets(10));


        //Creation de Service
        Brigade<Cuisinier> brigade = new Brigade<>("Service du soir");
        for(Cuisinier c : membres){
            brigade.ajouterMembre(c);
        }

        Service serv = new Service("Service du soir", brigade);
        btnTraiter.setOnAction(e ->{
            try { serv.traiterProchaineCommande();
                if(!plats.isEmpty()){
                    plats.remove(0);
                }
                }catch(ServiceSurchargeException ex) {
                System.out.println(ex.getMessage());
            }});

        btnAjouter.setOnAction(e ->{
            Plat nouveau = new Plat("Plat test", 15, List.of("ingredients"));
            serv.ajouterCommande(nouveau);
            plats.add(nouveau.getNom() + "-" + nouveau.getStatut());
        });

        //Creation d'une nouvelle colonnne
        TableColumn<Cuisinier, String> role = new TableColumn("Rôle");
        role.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(
                    data.getValue().getClass().getSimpleName()
            ));

        tableau.getColumns().add(role);

        /* data.getValue() retourne l'objet Cuisinier de la ligne, et on appelle
        getClass().getSimpleName() dessus pour avoir son type exact. */

        TextField champPrenom = new TextField();
        TextField champNom = new TextField();
        TextField champPoste = new TextField();

        champPrenom.setPromptText("Prenom");
        champNom.setPromptText("Nom");
        champPoste.setPromptText("Poste");

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("ChefExecutif","SousChef","ChefDePartie","Commis");
        roleBox.setPromptText("Choisir un rôle");

        HBox formulaire = new HBox(10, champPrenom, champNom, champPoste, roleBox, btnConfirmer);
        formulaire.setPadding(new Insets(10));
        root.setTop(formulaire);

        btnConfirmer.setOnAction(e -> {
            String saisiePrenom = champPrenom.getText();
            String saisieNom = champNom.getText();
            String saisiePoste = champPoste.getText();

                    if (saisiePrenom.isEmpty()
                            || saisieNom.isEmpty()
                            || saisiePoste.isEmpty()
                            || roleBox.getValue() == null) {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attention");
                        alert.setHeaderText("Champs manquants");
                        alert.setContentText("Veuillez remplir tous les champs.");
                        alert.showAndWait();
                    return;
                    }

            switch (roleBox.getValue()){
                case "ChefExecutif" -> membres.add(new ChefExecutif(saisieNom, saisiePrenom, saisiePoste));
                case "SousChef" -> membres.add(new SousChef(saisieNom, saisiePrenom, saisiePoste));
                case "ChefDePartie" -> membres.add(new ChefDePartie(saisieNom, saisiePrenom, saisiePoste));
                case "Commis" -> membres.add(new Commis(saisieNom, saisiePrenom, saisiePoste));
            }
            champPrenom.clear();
            champNom.clear();
            champPoste.clear();
            roleBox.setValue(null);
                });





    stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
