package restaurant.main;

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

public class App extends Application{

    public void start(Stage stage){
    stage.setTitle("Restauration");


    TableView<Cuisinier> tableau = new TableView();
    TableColumn<Cuisinier, String> nom = new TableColumn<>("Nom");
    TableColumn<Cuisinier, String> poste = new TableColumn<>("Poste");
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    poste.setCellValueFactory(new PropertyValueFactory<>("poste"));

    tableau.getColumns().addAll(nom, poste);

    Scene scene = new Scene(tableau, 600, 400);

    stage.setScene(scene);

    ObservableList<Cuisinier> membres = FXCollections.observableArrayList();
        membres.add(new ChefExecutif("Morel", "Lucas", "Chef de cuisine"));
        membres.add(new SousChef("Vuillemin", "Emma", "Sous Cheffe de Cuisine"));
        membres.add(new ChefDePartie("Girard", "Nathan", "Chef de Partie pour les sauces"));
        membres.add(new ChefDePartie("Perret", "Chloé", "Chef de Partie pour les pâtisseries"));
        membres.add(new Commis("Marchand", "Hugo", "Entrées"));
        membres.add(new Commis("Favre", "Sarah", "Desserts (avec Chloé)"));
        membres.add(new Commis("Piguet", "Laura", "Viandes"));

        tableau.setItems(membres);









    stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
