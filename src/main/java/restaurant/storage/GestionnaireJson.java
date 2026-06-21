package restaurant.storage;

import restaurant.model.plat.Plat;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class GestionnaireJson {

    public void sauvegarderPlat(Plat plat, String cheminFichier) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            gson.toJson(plat, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
