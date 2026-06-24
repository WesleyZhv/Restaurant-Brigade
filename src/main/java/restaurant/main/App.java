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






    stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
