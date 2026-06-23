package restaurant.storage;

import restaurant.model.plat.Plat;
import restaurant.model.personnel.Cuisinier;
import restaurant.model.*;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.GsonBuilder;
import typeadapters.RuntimeTypeAdapterFactory;
import restaurant.model.personnel.cuisine.ChefDePartie;
import restaurant.model.personnel.cuisine.ChefExecutif;
import restaurant.model.personnel.cuisine.Commis;
import restaurant.model.personnel.cuisine.SousChef;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class GestionnaireJson {

    private final RuntimeTypeAdapterFactory<Cuisinier> factory = RuntimeTypeAdapterFactory
            .of(Cuisinier.class, "type")
            .registerSubtype(ChefExecutif.class, "ChefExecutif")
            .registerSubtype(SousChef.class, "SousChef")
            .registerSubtype(ChefDePartie.class, "ChefDePartie")
            .registerSubtype(Commis.class, "Commis");

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(factory)
            .create();

    public void sauvegarderPlat(Plat plat, String cheminFichier) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            gson.toJson(plat, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // LIMITATION CONNUE : la sauvegarde de Brigade fonctionne (le JSON est généré),
// mais le rechargement avec polymorphisme complet (ChefExecutif, Commis, etc.)
// ne fonctionne pas encore. Le champ "type" n'est pas injecté par RuntimeTypeAdapterFactory
// à cause du type erasure sur ArrayList<T> dans la classe Brigade générique.
// Amélioration future : écrire un TypeAdapter custom pour Brigade, ou refactoriser
// la sérialisation pour traiter chaque membre individuellement.

    public Plat chargerPlat(String cheminFichier){
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(cheminFichier)){
            Plat plat1 = gson.fromJson(reader, Plat.class);
            return plat1;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void sauvegarderBrigade(Brigade<Cuisinier> brigade, String cheminFichier){
        RuntimeTypeAdapterFactory<Cuisinier> factory = RuntimeTypeAdapterFactory
                .of(Cuisinier.class, "type")
                .registerSubtype(ChefExecutif.class, "ChefExecutif")
                .registerSubtype(SousChef.class, "SousChef")
                .registerSubtype(ChefDePartie.class, "ChefDePartie")
                .registerSubtype(Commis.class, "Commis");

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(factory)
                .create();

        try(FileWriter writer = new FileWriter(cheminFichier)) {
            gson.toJson(brigade, writer);
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    // Brigade<Cuisinier> est un type générique : Gson seul ne peut pas connaître
// le type exact à l'intérieur des chevrons (à cause du "type erasure" de Java).
// TypeToken capture cette info complète pour que Gson sache reconstruire
// correctement chaque Cuisinier (et sa bonne sous-classe) à l'intérieur de la Brigade.
    public Brigade<Cuisinier> chargerBrigade(String cheminFichier){

        RuntimeTypeAdapterFactory<Cuisinier> factory = RuntimeTypeAdapterFactory
                .of(Cuisinier.class, "type")
                .registerSubtype(ChefExecutif.class, "ChefExecutif")
                .registerSubtype(SousChef.class, "SousChef")
                .registerSubtype(ChefDePartie.class, "ChefDePartie")
                .registerSubtype(Commis.class, "Commis");

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(factory)
                .create();

        try(FileReader reader = new FileReader(cheminFichier)) {

            // permet à Gson de comprendre le type générique complet
            Type brigadeType = new TypeToken<Brigade<Cuisinier>>(){}.getType();
            Brigade<Cuisinier> brig = gson.fromJson(reader, brigadeType);

            return brig;

        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}